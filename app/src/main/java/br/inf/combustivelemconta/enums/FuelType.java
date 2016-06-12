package br.inf.combustivelemconta.enums;

/**
 * Created by anapaula on 6/11/16.
 */
public enum FuelType {
    GASOLINE("Gasoline"), ETHANOL("Ethanol"), DIESEL("Diesel"), ADDITIVE("Additive");

    private String value;

    FuelType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}