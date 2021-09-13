/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.api.Location.dto;

/**
 *
 * @author Freddy
 */
public class CityDTO {
    
    private String name;
    private String latitude;
    private String longitud;
    private double score;

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitud() {
        return longitud;
    }

    public double getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
}
