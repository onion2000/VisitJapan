package com.example.oniononion.comp4521project.Object;

/**
 * Created by oniononion on 6/3/2016.
 */
public class WeatherInfo {

    private String city_name;
    private String image_url;
    private String high_temp;
    private String low_temp;
    private String prob_rain;

    public WeatherInfo(String city_name,String image_url, String high_temp, String low_temp, String prob_rain){
        this.city_name =city_name;
        this.image_url=image_url;
        this.high_temp=high_temp;
        this.low_temp=low_temp;
        this.prob_rain=prob_rain;
    }
    public WeatherInfo(){
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProb_rain() {
        return prob_rain;
    }

    public void setProb_rain(String prob_rain) {
        this.prob_rain = prob_rain;
    }

    public String getLow_temp() {
        return low_temp;
    }

    public void setLow_temp(String low_temp) {
        this.low_temp = low_temp;
    }

    public String getHigh_temp() {
        return high_temp;
    }

    public void setHigh_temp(String high_temp) {
        this.high_temp = high_temp;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }



}
