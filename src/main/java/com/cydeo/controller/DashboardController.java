package com.cydeo.controller;

import com.cydeo.client.ConsumeCurrencyClient;
import com.cydeo.service.CompanyService;
import com.cydeo.service.DashboardService;
import com.cydeo.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    private final InvoiceService invoiceService;
    private final CompanyService companyService;
    private final ConsumeCurrencyClient consumeCurrencyClient;

    public DashboardController(DashboardService dashboardService, InvoiceService invoiceService, CompanyService companyService, ConsumeCurrencyClient consumeCurrencyClient) {
        this.dashboardService = dashboardService;
        this.invoiceService = invoiceService;
        this.companyService = companyService;
        this.consumeCurrencyClient = consumeCurrencyClient;
    }

    @GetMapping("/dashboard")
    public String navigateToDashboard(Model model) throws Exception {
        model.addAttribute("companyTitle", companyService.getCompanyByLoggedInUser().getTitle());
        model.addAttribute("summaryNumbers", dashboardService.getSummaryNumbers());
        model.addAttribute("invoices", dashboardService.getLast3TransactionsByDate());
        model.addAttribute("exchangeRates", dashboardService.getExchangeRates());
        model.addAttribute("title", "Cydeo Accounting-Dashboard");



        return "dashboard.html";
    }
}
