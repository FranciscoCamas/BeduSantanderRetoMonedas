package org.bedu.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.bedu.rest.model.Currency;
import org.bedu.rest.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("currencies")
@Validated
public class CurrencyController {

    @Autowired
    private CurrencyService currencies;

    public CurrencyController(CurrencyService currencies ){
     this.currencies=currencies;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Currency> getCurrencies(){
        return currencies.getAll() ;
    }

    @GetMapping("{shortName}")
        public  List<Currency>  getCurrencyByShortName(@PathVariable("shortName") String shortName ) {

            return  this.currencies.getExchange(shortName);
    }

    @GetMapping("{shortName}/exchanges/{amount}")
    public  List<Currency>  getCurrencyExchange(@PathVariable("shortName") String shortName,@PathVariable("amount") double amount ) {

        return  this.currencies.getExchange(shortName,amount);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Currency createCurrency(@Valid @RequestBody Currency currency) {

        return currencies.add(currency);
    }

    @PutMapping("{shortName}")
    public void updateCurrency( @RequestBody Currency currency ,
                          @PathVariable("shortName") String shortName){
        currencies.update(shortName,currency);
    }

    @DeleteMapping("{shortName}")
    public void deleteContact(@Valid @PathVariable("shortName")  @Size(min=3) String shortName)  {
        currencies.remove(shortName);
    }

}