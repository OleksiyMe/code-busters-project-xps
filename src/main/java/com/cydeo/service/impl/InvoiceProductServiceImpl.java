package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.*;
import com.cydeo.entity.InvoiceProduct;
import com.cydeo.entity.Product;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceService invoiceService;
    private final ProductService productService;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;


    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository,
                                     @Lazy InvoiceService invoiceService, ProductService productService,
                                     MapperUtil mapperUtil, SecurityService securityService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public InvoiceProductDto findInvoiceProductById(long id) {
        return mapperUtil.convert(invoiceProductRepository.findInvoiceProductById(id), new InvoiceProductDto());
    }

    @Override
    public List<InvoiceProductDto> getInvoiceProductsByInvoiceId(Long invoiceId) {
        return invoiceProductRepository.findAllByInvoice_Id(invoiceId)
                .stream()
                .sorted(Comparator.comparing((InvoiceProduct each) -> each.getInvoice().getInvoiceNo()).reversed())
                .map(each -> {
                    InvoiceProductDto dto = mapperUtil.convert(each, new InvoiceProductDto());
                    dto.setTotal(each.getPrice().multiply(BigDecimal.valueOf(each.getQuantity() * (each.getTax() + 100) / 100d)));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void save(Long invoiceId, InvoiceProductDto invoiceProductDto) {
        Invoice invoice = mapperUtil.convert(invoiceService.findInvoiceById(invoiceId), new Invoice());
        InvoiceProduct invoiceProduct = mapperUtil.convert(invoiceProductDto, new InvoiceProduct());
        invoiceProduct.setInvoice(invoice);
        invoiceProduct.setProfitLoss(BigDecimal.ZERO);
        invoiceProductRepository.save(invoiceProduct);

    }

    @Override
    public void SoftDelete(Long invoiceProductId) {

        InvoiceProduct invoiceProduct = mapperUtil.convert(findInvoiceProductById(invoiceProductId),
                new InvoiceProduct());
        invoiceProduct.setIsDeleted(true);
        invoiceProductRepository.save(invoiceProduct);
    }

    @Override
    public void deleteIpByInvoiceId(Long id) {
        UserDto loggedInUser = securityService.getLoggedInUser(); //got the logged in User

        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoice_Id(id);//found all the related InvoiceProducts

        invoiceProductList.stream().filter(invoiceProduct -> invoiceProduct.getInvoice().getCompany().getId()
                .equals(loggedInUser.getCompany().getId())).forEach(invoiceProduct -> SoftDelete(invoiceProduct.getId()));
        //if Id of invoiceProducts of that Invoice match the Id of that logged in user's Company Id then delete each of the Invoice Products

    }


    @Override
    public void completeApprovalProcedures(Long invoiceId, InvoiceType type) {

        List<InvoiceProduct> invoiceProducts = invoiceProductRepository.findAllByInvoice_Id(invoiceId);

        if (type.getValue().equals("Purchase")) {
            for (InvoiceProduct invoiceProduct : invoiceProducts) {
                Product product = invoiceProduct.getProduct();
                product.setQuantityInStock(product.getQuantityInStock() + invoiceProduct.getQuantity());

                InvoiceProductDto invoiceProductDto = mapperUtil.convert(invoiceProduct, new InvoiceProductDto());
                invoiceProductDto.setRemainingQuantity(invoiceProduct.getQuantity());

                invoiceProductRepository.save(mapperUtil.convert(invoiceProductDto, new InvoiceProduct()));
            }
        } else {
            for (InvoiceProduct salesInvoiceProduct : invoiceProducts) {
                Product product = salesInvoiceProduct.getProduct();

                if (checkProductQuantity(mapperUtil.convert(salesInvoiceProduct, new InvoiceProductDto()))) {

                    product.setQuantityInStock(product.getQuantityInStock() - salesInvoiceProduct.getQuantity());

                    InvoiceProductDto salesInvoiceProductDto = mapperUtil.convert(salesInvoiceProduct, new InvoiceProductDto());

                    salesInvoiceProductDto.setRemainingQuantity(salesInvoiceProduct.getQuantity());

                    calculateTotalPrice(salesInvoiceProductDto);

                    salesInvoiceProduct.setProfitLoss(invoiceService.calculateProfitLossForInvoiceProduct(salesInvoiceProductDto));

                    invoiceProductRepository.save(salesInvoiceProduct);

                } else {
                    throw new RuntimeException("Not enough products for sale");
                }
            }
        }
    }

    @Override
    public boolean checkProductQuantity(InvoiceProductDto salesInvoiceProduct) {
        return salesInvoiceProduct.getProduct().getQuantityInStock() >= salesInvoiceProduct.getQuantity();
    }

    @Override
    public List<InvoiceProduct> findInvoiceProductsByInvoiceTypeAndProductRemainingQuantity(InvoiceType type, Product product, Integer remainingQuantity) {
        return invoiceProductRepository.findInvoiceProductsByInvoiceInvoiceTypeAndProductAndRemainingQuantityNotOrderByIdAsc(type, product, remainingQuantity);
    }

    @Override
    public List<InvoiceProduct> findAllInvoiceProductsByProductId(Long id) {
        return invoiceProductRepository.findAllInvoiceProductByProductId(id);
    }

    @Override
    public List<InvoiceProductDto> findAllNotDeleted() {
        return invoiceProductRepository.findAll().stream()
                .filter(invoiceProduct -> invoiceProduct.getIsDeleted().equals(false))
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceProductDto> findAllInvoiceProducts() {
        return invoiceProductRepository.findAll().stream()
                .filter(invoiceProduct -> invoiceProduct.getInvoice().getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceProductDto> findAllNotDeletedForCurrentCompany() {

        UserDto loggedInUser = securityService.getLoggedInUser();

        return findAllNotDeleted().stream()
                .filter(invoiceProductDto -> invoiceProductDto.getInvoice().getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .collect(Collectors.toList());

    }

    @Override
    public List<InvoiceProductDto> findAllNotDeletedForCurrentCompanySortByDate() {
        UserDto loggedInUser = securityService.getLoggedInUser();
        return invoiceProductRepository.findAllByIsDeletedFalseOrderByInvoiceLastUpdateDateTimeDesc().stream()
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto()))
                .filter(invoiceProductDto -> invoiceProductDto.getInvoice().getCompany().getId()
                        .equals(loggedInUser.getCompany().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void addToRepository(InvoiceProduct invoiceProduct) {
        invoiceProductRepository.save(invoiceProduct);
    }

    private void calculateTotalPrice(InvoiceProductDto invoiceProductDto) {
        BigDecimal price = BigDecimal.valueOf(invoiceProductDto.getQuantity()).multiply(invoiceProductDto.getPrice());
        BigDecimal tax = price.multiply(BigDecimal.valueOf(invoiceProductDto.getTax())).divide(BigDecimal.valueOf(100));
        invoiceProductDto.setTotal(price.add(tax));
    }

}
