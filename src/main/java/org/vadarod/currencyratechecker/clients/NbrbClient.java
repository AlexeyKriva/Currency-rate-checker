package org.vadarod.currencyratechecker.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vadarod.currencyratechecker.entities.CurrencyDto;

import java.util.List;

@FeignClient(name = "nbrbClient", url = "https://api.nbrb.by/exrates")
public interface NbrbClient {
    @GetMapping("/rates")
    List<CurrencyDto> getCurrencyRatesByDate(@RequestParam("ondate") String date,
                                             @RequestParam("periodicity") int periodicity);
}
