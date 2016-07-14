package br.inf.combustivelemconta.models;

import com.google.firebase.database.Exclude;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.inf.combustivelemconta.enums.FuelType;

public class Price {
    private double price;
    private Date date;
    private FuelType fuelType;

    public Price() {}

    public Price(double price, Date date, FuelType fuelType) {
        this.price = price;
        this.date = date;
        this.fuelType = fuelType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("price", price);
        result.put("date", date);
        result.put("fuelType", fuelType.getValue());

        return result;
    }
}
