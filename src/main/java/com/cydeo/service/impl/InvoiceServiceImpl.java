package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.dto.ProductDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.ClientVendor;
import com.cydeo.entity.Company;
import com.cydeo.entity.Invoice;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductService invoiceProductService;
    private final SecurityService securityService;
    private final MapperUtil mapperUtil;
    private final ProductService productService;


    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceProductService invoiceProductService, SecurityService securityService, MapperUtil mapperUtil, ProductService productService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductService = invoiceProductService;
        this.securityService = securityService;
        this.mapperUtil = mapperUtil;
        this.productService = productService;
    }

    @Override
    public InvoiceDto findInvoiceById(Long id) {
//        InvoiceDto invoiceDto = mapperUtil.convert(invoiceRepository.findById(id), new InvoiceDto());
//        List<InvoiceProductDto> list = invoiceProductService.getInvoiceProductsByInvoiceId(id);
//        invoiceDto.setInvoiceProducts(list);
//        return invoiceDto;
//I added 3 calculated fields in listAllNotDeletedInvoicesForLoggedInUser() method.
//No sense to repeat it here, so I modified code. OleksiyMe
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
        return invoiceRepository.findAllNotDeleted().stream()
                .filter(invoice -> invoice.getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .map(invoice -> {
                    InvoiceDto invoiceDto = mapperUtil.convert(invoice, new InvoiceDto());
                    List<InvoiceProductDto> list =
                            invoiceProductService.getInvoiceProductsByInvoiceId(invoice.getId());
                    BigDecimal price = new BigDecimal(0);
                    BigDecimal tax = new BigDecimal(0);
                    BigDecimal total = new BigDecimal(0);
                    for (InvoiceProductDto eachProduct : list) {
                        BigDecimal eachTotalBeforeTax =
                                eachProduct.getPrice().multiply(BigDecimal.valueOf(eachProduct.getQuantity()));
                        BigDecimal eachTaxAmount = eachProduct.getTotal().subtract(eachTotalBeforeTax);
                        price = price.add(eachTotalBeforeTax);
                        tax = tax.add(eachTaxAmount);
                        total = total.add(eachProduct.getTotal());
                    }
                    invoiceDto.setPrice(price);
                    invoiceDto.setTax(tax);
                    invoiceDto.setTotal(total);
                    invoiceDto.setInvoiceProducts(list);
                    return invoiceDto;
                })
                .collect(Collectors.toList());
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
                {invoice.setIsDeleted(true); }                                    //soft delete that invoice

            if(currentCompany.getCompanyStatus().equals("Vendor")){    //if it is a Vendor all the related InvoiceProducts should also be deleted
            invoiceProductService.deleteIpByInvoiceId(id); }   //delete the related InvoiceProducts of that invoice as well

            invoiceRepository.save(invoice);                //save to repo to have a soft delete

        }


    @Override
    public InvoiceDto createPurchaseInvoice(InvoiceDto invoiceDto) {

        Invoice invoice = mapperUtil.convert(invoiceDto, new Invoice());
        invoice.setInvoiceNo(invoiceDto.getInvoiceNo());
        invoice.setDate(invoiceDto.getDate());
        invoice.setClientVendor(mapperUtil.convert(invoiceDto.getClientVendor(), new ClientVendor()));
        invoice.setInvoiceType(InvoiceType.PURCHASE);
        invoice.setInvoiceStatus(InvoiceStatus.AWAITING_APPROVAL);
        invoice.setId(invoiceDto.getId());

        invoiceRepository.save(invoice);
        return invoiceDto;
    }

    @Override
    public String generatePurchaseInvoiceNumber() {
        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        String max = invoiceRepository.findMaxId(currentCompany.getId()).toString();

        String num = "";

        for (int i = 0; i < max.length(); i++) {
            if (Character.isDigit(max.charAt(i))) num += max.charAt(i);
        }

        return "P-" + String.format("%03d", Integer.parseInt(num) + 1);
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
        InvoiceDto invoiceDto = findInvoiceById(id);
        invoiceDto.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoiceDto.setDate(LocalDate.now());
        for (InvoiceProductDto invoiceProductDto : invoiceProductService.getInvoiceProductsByInvoiceId(id)) {
            ProductDto productDto = productService.findProductById(invoiceProductDto.getProduct().getId());
            productDto.setQuantityInStock(productDto.getQuantityInStock() + invoiceProductDto.getQuantity());
            productService.save(productDto);
        }
        invoiceRepository.save(mapperUtil.convert(invoiceDto, new Invoice()));
    }

    @Override
    public List<InvoiceDto> listAllSalesInvoices() {
        List<InvoiceDto> list = listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceType().equals(InvoiceType.SALES))
                .collect(Collectors.toList());
        return list;
    }


}
