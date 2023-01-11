package com.cydeo.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyDto {

    private BigDecimal euro;
    private BigDecimal britishPound;
    private BigDecimal indianRupee;
    private BigDecimal japaneseYen;
    private BigDecimal canadianDollar;
}
