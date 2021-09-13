/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.api.Location.Entity;

import java.util.List;

/**
 *
 * @author Freddy
 */
public class City {
    private int id;
    private String name;
    private String ascii;
    private String altCompleteNames;
    private List<String> altNames;
    private String latitud;
    private String longitud;
    private String fClass;
    private String fCode;
    private String country;
    private String admin1;

    public City(int id, String name, String ascii, String altCompleteNames, String latitud, String longitud, String fClass, String fCode, String country, String admin1) {
        this.id = id;
        this.name = name;
        this.ascii = ascii;
        this.altCompleteNames = altCompleteNames;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fClass = fClass;
        this.fCode = fCode;
        this.country= country;
        this.admin1 = admin1;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAltCompleteNames() {
        return altCompleteNames;
    }

    public String getAscii() {
        return ascii;
    }

    public List<String> getAltNames() {
        return altNames;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getfClass() {
        return fClass;
    }

    public String getfCode() {
        return fCode;
    }

    public String getCountry() {
        return country;
    }

    public String getAdmin1() {
        return admin1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAscii(String ascii) {
        this.ascii = ascii;
    }

    public void setAltCompleteNames(String altCompleteNames) {
        this.altCompleteNames = altCompleteNames;
    }

    public void setAltNames(List<String> altNames) {
        this.altNames = altNames;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setfClass(String fClass) {
        this.fClass = fClass;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name=" + name + ", ascii=" + ascii + ", altNames=" + altNames + ", latitud=" + latitud + ", longitud=" + longitud + ", fClass=" + fClass + ", fCode=" + fCode + ", Country=" + country + ", admin1=" + admin1 + '}';
    }

    
    
}

