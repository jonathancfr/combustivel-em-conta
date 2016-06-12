package br.inf.combustivelemconta.models;

import java.util.Calendar;

public class Price {
    private float price;
    private Calendar date;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
