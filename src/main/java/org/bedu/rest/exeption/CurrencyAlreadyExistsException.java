package org.bedu.rest.exeption;

public class CurrencyAlreadyExistsException extends RuntimeException{
    String name;

    public CurrencyAlreadyExistsException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
