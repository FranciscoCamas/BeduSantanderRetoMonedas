package org.bedu.rest.model;

import jakarta.validation.constraints.*;

public class Currency {

    @NotBlank
    private String name;
    @NotBlank
    private String shortName;

    @Positive(message = "Debe de ingresar un número positivo")
    private double exChange;

    public Currency(String name, String shortName,double exChange) {
        this.name=name;
        this.shortName=shortName;
        this.exChange=exChange;
    }

    public Currency updateByCurrency(Currency anotherCurrency) {
         // This not, because is the key to the objet currency
        //this.name=anotherCurrency.name;
        this.shortName=anotherCurrency.getShortName();
        this.exChange=anotherCurrency.getExChange();

        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shorName) {
        this.shortName = shorName;
    }

    public double getExChange() {
        return exChange;
    }

    public void setExChange(double exChange) {
        this.exChange = exChange;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}