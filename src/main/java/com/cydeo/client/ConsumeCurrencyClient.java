package com.cydeo.client;


import com.cydeo.dto.feign.ConsumedCurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url="https://api.exchangerate.host",name = "CURRENCY-CLIENT")
public interface ConsumeCurrencyClient {


    @GetMapping("/latest?base=USD")
    ConsumedCurrencyDto getCurrency();


}
