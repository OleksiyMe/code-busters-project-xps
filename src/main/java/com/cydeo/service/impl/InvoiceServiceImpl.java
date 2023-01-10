package com.cydeo.service.impl;

import com.cydeo.dto.*;
import com.cydeo.entity.*;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.repository.InvoiceRepository;
import com.cydeo.service.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductService invoiceProductService;
    private final SecurityService securityService;
    private final MapperUtil mapperUtil;
    private final ProductService productService;
    private final ClientVendorService clientVendorService;


    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceProductService invoiceProductService,
                              SecurityService securityService, MapperUtil mapperUtil, ProductService productService,
                              @Lazy ClientVendorService clientVendorService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductService = invoiceProductService;
        this.securityService = securityService;
        this.mapperUtil = mapperUtil;
        this.productService = productService;
        this.clientVendorService = clientVendorService;
    }

    @Override
    public InvoiceDto findInvoiceById(Long id) {
        List<InvoiceDto> list = listAllNotDeletedInvoicesForLoggedInUser();
        return list.stream().filter(invoiceDto -> invoiceDto.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("No Invoice with this id " + id));
    }

    @Override
    public void createInvoice(InvoiceDto invoice) {

    }

    @Override
    public List<InvoiceDto> listAllNotDeletedInvoicesForLoggedInUser() {
        UserDto loggedInUser = securityService.getLoggedInUser();

        List<InvoiceDto> listOfInvoicesDto = invoiceRepository.findAllNotDeleted().stream()
                .filter(invoice -> invoice.getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .map(invoice -> {
                    InvoiceDto invoiceDto = mapperUtil.convert(invoice, new InvoiceDto());
                    List<InvoiceProductDto> list =
                            invoiceProductService.getInvoiceProductsByInvoiceId(invoice.getId());
                    BigDecimal price = new BigDecimal(0);
                    BigDecimal tax = new BigDecimal(0);
                    BigDecimal total = new BigDecimal(0);
//                    BigDecimal profitLoss = new BigDecimal(0);
                    for (InvoiceProductDto eachProduct : list) {
                        BigDecimal eachTotalBeforeTax =
                                eachProduct.getPrice().multiply(BigDecimal.valueOf(eachProduct.getQuantity()));
                        BigDecimal eachTaxAmount = eachProduct.getTotal().subtract(eachTotalBeforeTax);
                        price = price.add(eachTotalBeforeTax);
                        tax = tax.add(eachTaxAmount);
                        total = total.add(eachProduct.getTotal());
//                        profitLoss = profitLoss.add(eachProduct.getProfitLoss());
                    }
                    invoiceDto.setPrice(price);
                    invoiceDto.setTax(tax);
                    invoiceDto.setTotal(total);
                    invoiceDto.setInvoiceProducts(list);
                    return invoiceDto;
                })
                .collect(Collectors.toList());
        return listOfInvoicesDto;
    }

    @Override
    public InvoiceDto updateInvoice(Long id) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {
        UserDto loggedInUser = securityService.getLoggedInUser(); //got the logged in User
        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company()); //Got &Converted logged In User's CompDto to Entity

        Invoice invoice = invoiceRepository.findById(id).get();  //found the invoice by its Id from Repo

        if (currentCompany.getId().equals(invoice.getCompany().getId()))   //if the logged in User' and Invoice' id match
        {
            invoice.setIsDeleted(true);
        }                                    //soft delete that invoice

        if (currentCompany.getCompanyStatus().equals("Vendor")) {    //if it is a Vendor all the related InvoiceProducts should also be deleted
            invoiceProductService.deleteIpByInvoiceId(id);
        }   //delete the related InvoiceProducts of that invoice as well

        invoiceRepository.save(invoice);                //save to repo to have a soft delete

    }


    @Override
    public InvoiceDto save(InvoiceDto invoiceDto) {

        User user = mapperUtil.convert(securityService.getLoggedInUser(), new User());

        Invoice invoice = mapperUtil.convert(invoiceDto, new Invoice());
        invoice.setInvoiceNo(invoiceDto.getInvoiceNo());
        invoice.setDate(invoiceDto.getDate());
        invoice.setInvoiceType(invoiceDto.getInvoiceType());
        invoice.setCompany(user.getCompany());
        invoice.setInvoiceStatus(InvoiceStatus.AWAITING_APPROVAL);
        invoice.setClientVendor(mapperUtil.convert(invoiceDto.getClientVendor(), new ClientVendor()));
        invoice = invoiceRepository.save(invoice);
        if (invoiceDto.getInvoiceProducts() != null) {
            for (InvoiceProductDto invoiceProduct : invoiceDto.getInvoiceProducts()) {
                invoiceProductService.save(invoice.getId(), invoiceProduct);
            }
        }
        return mapperUtil.convert(invoice, new InvoiceDto());
    }

    @Override
    public String generateInvoiceNumber(InvoiceType invoiceType) {
        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        String maxInvoiceId = invoiceRepository.findMaxId(currentCompany.getId()).toString();
        String maxSalesId = invoiceRepository.findMaxSalesId(currentCompany.getId()).toString();

        String num = "";

        if (invoiceType.getValue().equals("Purchase")) {
            for (int i = 0; i < maxInvoiceId.length(); i++) {
                if (Character.isDigit(maxInvoiceId.charAt(i))) num += maxInvoiceId.charAt(i);
            }
            return "P-" + String.format("%03d", Integer.parseInt(num) + 1);

        } else {
            for (int i = 0; i < maxSalesId.length(); i++) {
                if (Character.isDigit(maxSalesId.charAt(i))) num += maxSalesId.charAt(i);
            }
            return "S-" + String.format("%03d", Integer.parseInt(num) + 1);
        }

    }

    @Override
    public List<InvoiceDto> listAllPurchaseInvoices() {

        List<InvoiceDto> list = listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceType().equals(InvoiceType.PURCHASE))
                .collect(Collectors.toList());
        return list;
    }


    @Override
    public void approve(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoice.setDate(LocalDate.now());
        invoiceProductService.completeApprovalProcedures(id, invoice.getInvoiceType());
        invoiceRepository.save(invoice);
    }

    @Override
    public List<InvoiceDto> listAllSalesInvoices() {
        List<InvoiceDto> list = listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceType().equals(InvoiceType.SALES))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<InvoiceDto> getLastThreeInvoices() {
        UserDto loggedInUser = securityService.getLoggedInUser();

        return invoiceProductService.findAllInvoiceProducts().stream()
                .filter(invoiceProduct -> invoiceProduct.getInvoice().getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .map(invoiceProduct -> {

                    BigDecimal price = BigDecimal.valueOf(invoiceProduct.getQuantity()).multiply(invoiceProduct.getPrice());
                    BigDecimal tax = price.multiply(BigDecimal.valueOf(invoiceProduct.getTax())).divide(BigDecimal.valueOf(100));
                    BigDecimal total = price.add(tax);

                    InvoiceDto invoiceDto = new InvoiceDto();
                    invoiceDto.setInvoiceNo(invoiceProduct.getInvoice().getInvoiceNo());
                    invoiceDto.setDate(invoiceProduct.getInvoice().getDate());
                    invoiceDto.setClientVendor(mapperUtil.convert(invoiceProduct.getInvoice().getClientVendor(), new ClientVendorDto()));
                    invoiceDto.setPrice(price.setScale(2, RoundingMode.CEILING));
                    invoiceDto.setTax(tax);
                    invoiceDto.setTotal(total.setScale(2, RoundingMode.CEILING));
                    return invoiceDto;

                })
                .sorted(comparing(InvoiceDto::getDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateProfitLossForInvoiceProduct(InvoiceProductDto salesInvoiceProduct) {

        Product product = mapperUtil.convert(salesInvoiceProduct.getProduct(), new Product());

        List<InvoiceProduct> purchaseInvoiceProducts = invoiceProductService
                .findInvoiceProductsByInvoiceTypeAndProductRemainingQuantity(InvoiceType.PURCHASE, product, 0);

        BigDecimal profitLoss = BigDecimal.ZERO;

        for (InvoiceProduct purchaseInvoiceProduct : purchaseInvoiceProducts) {

            InvoiceProductDto purchaseInvoiceProductDto = mapperUtil.convert(purchaseInvoiceProduct, new InvoiceProductDto());

            if(purchaseInvoiceProductDto.getRemainingQuantity() >= salesInvoiceProduct.getQuantity()){

                BigDecimal purchasePrice = BigDecimal.valueOf(salesInvoiceProduct.getRemainingQuantity())
                        .multiply((purchaseInvoiceProductDto.getPrice().multiply(BigDecimal.valueOf(((purchaseInvoiceProductDto.getTax()) + 100) / 100))));

                BigDecimal salesPrice = BigDecimal.valueOf(purchaseInvoiceProduct.getRemainingQuantity())
                        .multiply((salesInvoiceProduct.getPrice().multiply(BigDecimal.valueOf(((salesInvoiceProduct.getTax()) + 100) / 100))));

                profitLoss = salesInvoiceProduct.getProfitLoss().add(salesPrice.subtract(purchasePrice));

                purchaseInvoiceProduct.setRemainingQuantity(purchaseInvoiceProduct.getRemainingQuantity() - salesInvoiceProduct.getRemainingQuantity());
                salesInvoiceProduct.setProfitLoss(profitLoss);
                salesInvoiceProduct.setRemainingQuantity(0);

                invoiceProductService.addToRepository(mapperUtil.convert(salesInvoiceProduct, new InvoiceProduct()));
                invoiceProductService.addToRepository(mapperUtil.convert(purchaseInvoiceProductDto, new InvoiceProduct()));

                break;

            } else {

                BigDecimal purchasePrice = BigDecimal.valueOf(salesInvoiceProduct.getRemainingQuantity())
                        .multiply((purchaseInvoiceProductDto.getPrice().multiply(BigDecimal.valueOf(((purchaseInvoiceProductDto.getTax()) + 100) / 100))));

                BigDecimal salesPrice = BigDecimal.valueOf(salesInvoiceProduct.getRemainingQuantity())
                        .multiply((salesInvoiceProduct.getPrice().multiply(BigDecimal.valueOf(((salesInvoiceProduct.getTax()) + 100) / 100))));

                profitLoss = salesInvoiceProduct.getProfitLoss().add(salesPrice.subtract(purchasePrice));

                salesInvoiceProduct.setRemainingQuantity(salesInvoiceProduct.getRemainingQuantity() - purchaseInvoiceProduct.getRemainingQuantity());
                salesInvoiceProduct.setProfitLoss(profitLoss);
                purchaseInvoiceProductDto.setRemainingQuantity(0);

                invoiceProductService.addToRepository(mapperUtil.convert(salesInvoiceProduct, new InvoiceProduct()));
                invoiceProductService.addToRepository(mapperUtil.convert(purchaseInvoiceProductDto, new InvoiceProduct()));

            }

        }
        return profitLoss;

    }

    @Override
    public String invoiceCanBePrinted(Long invoiceId) {
        UserDto loggedInUser =securityService.getLoggedInUser();
        //if we are trying to print invoice from other company, or deleted invoice - return error
       if (!listAllNotDeletedInvoicesForLoggedInUser().stream()
                .map(invoiceDto -> invoiceDto.getId())
                .anyMatch(id->id==invoiceId))
           return "Wrong invoice id";

       return "";
    }


}
