package com.example.oniononion.comp4521project.Object;

/**
 * Created by oniononion on 12/3/2016.
 */
public class Currency {

    private String full_name;
    private String short_name;
    private float exchange_rate;
    private static int counter =0;

    public Currency(String full_name, float exchange_rate) {
        this.full_name = full_name;
        this.exchange_rate = exchange_rate;
        this.short_name = "aaa";
    }

    public float getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(float exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }


}
