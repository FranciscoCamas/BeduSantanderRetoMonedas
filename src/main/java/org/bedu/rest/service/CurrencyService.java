package org.bedu.rest.service;

import org.bedu.rest.exeption.CurrencyAlreadyExistsException;
import org.bedu.rest.exeption.CurrencyNotFoundException;
import org.bedu.rest.model.Currency;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private Map<String, Currency> currencies;
    public CurrencyService() {
        currencies = new HashMap<String, Currency>();
        this.currencies.put("MXN", new Currency("Mexican Peso",   "MXN",0.057));
        this.currencies.put("USD", new Currency("American Dollar","USD",0.057));
        this.currencies.put("EUR", new Currency("Euro",           "EUR",0.053));
    }

    public boolean exists(String shortName){

        return  currencies.containsKey(shortName.toUpperCase());
    }
    public List<Currency> getAll(){

        return new LinkedList<Currency>(currencies.values());
    }

    public Currency getOne(String shortName){

        if(!this.exists(shortName.toUpperCase()))
           throw new CurrencyNotFoundException(shortName.toUpperCase());

        return currencies.get(shortName.toUpperCase());
    }


    public List<Currency> getExchange(String shortName){

        if(!this.exists(shortName))
            throw new CurrencyNotFoundException(shortName);

        return this.getAll().stream().filter(  k-> !k.getShortName().toUpperCase().equals( shortName.toUpperCase())  ).toList();

    }

    private double calculaFactorCambio(Currency curentCurrency, Currency currencyTo){
        double factor ;
        if(currencyTo.getShortName().equals("MXN"))
            factor=curentCurrency.getExChange();
        else
            if(currencyTo.getShortName().equals("USD"))
               factor=1/curentCurrency.getExChange();
            else{

                if(curentCurrency.getShortName().equals("MXN"))
                    factor=1/currencyTo.getExChange();
                else{
                    Currency currencyUSD = currencies.get("USD");
                    factor= (1.0/currencyTo.getExChange())*currencyUSD.getExChange();
                }
            }
     return factor;
    }
    public List<Currency> getExchange(String shortName, double amount ){

        if(!this.exists(shortName))
            throw new CurrencyNotFoundException(shortName);

        Currency currencyTo = currencies.get(shortName);

        return   this.getExchange(shortName).stream()
                    .map( k -> new Currency (k.getName(),k.getShortName(), amount *calculaFactorCambio(k,currencyTo)))
                    .collect(Collectors.toList());
    }

    public Currency add(Currency newCurrency){

        if(this.exists(newCurrency.getShortName() ))
            throw new CurrencyAlreadyExistsException(newCurrency.getShortName());

        return currencies.put(newCurrency.getShortName(),newCurrency);

    }

    public void update(String shortName, Currency currency){

        if(!this.exists(shortName))
            throw new CurrencyNotFoundException(shortName);

        Currency current = currencies.get(shortName);
        current.updateByCurrency(currency) ;
    }

    public  void remove (String shortName){

        if(!this.exists(shortName))
            throw new CurrencyNotFoundException(shortName);

        currencies.remove(shortName);
    }

}