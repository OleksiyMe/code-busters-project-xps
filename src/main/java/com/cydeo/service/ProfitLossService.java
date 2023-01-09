package com.cydeo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface ProfitLossService {

    Map<LocalDate, BigDecimal> getProfitLossByMonth();
}
