package com.cydeo.service;

import com.cydeo.dto.InvoiceDto;

import java.util.List;

public interface DashboardService {

    List<InvoiceDto> getLast3TransactionsByDate();

}
