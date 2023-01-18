package com.cydeo.controller;

import com.cydeo.service.ReportingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/stockData")
    public String listStockReport(Model model) throws Exception {

        model.addAttribute("invoiceProducts",reportingService.getStockData());
        return "report/stock-report";
    }

    @GetMapping("/profitLossData")
    public String getPLReport(Model model) throws Exception {

        model.addAttribute("monthlyProfitLossDataMap",
                reportingService.getMonthlyProfitLossDataMap());

        return "report/profit-loss-report";

    }


}