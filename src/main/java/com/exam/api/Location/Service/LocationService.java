/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.api.Location.Service;

import com.exam.api.Location.Entity.City;
import com.exam.api.Location.Repository.LocationRepository;
import com.exam.api.Location.dto.CityDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Freddy
 */
@Service
public class LocationService {
    
    @Autowired
    LocationRepository locationRepository;
    
    public Map<String,List<CityDTO>> getCitiesByCoordenate(String q,String latitude, String longitud){
        Map<String,List<CityDTO>> response = new TreeMap<>();
        List<CityDTO> citiesDto = new ArrayList<>();
        List<City> foundCities = locationRepository.findByLatitudeAndLongitud(latitude, longitud);
        foundCities.stream().map((city) -> {
            CityDTO cityDto = new CityDTO();
            cityDto.setName(city.getName() + (city.getAdmin1() != null ? ( ", " + city.getAdmin1()) : "") + (", " + city.getCountry()));
            cityDto.setLatitude(city.getLatitud());
            cityDto.setLongitud(city.getLongitud());
            cityDto.setScore(1); //Se agrega score 1 porque las coordenadas son unicas
            return cityDto;
        }).forEachOrdered((cityDto) -> {
            citiesDto.add(cityDto);
        });
        Map<String,List<CityDTO>> foundName = this.getCitiesByName(q);
        citiesDto.addAll(foundName.get("suggestions"));
        response.put("suggestions", citiesDto);
        return response;
    }
    
    
    public Map<String,List<CityDTO>> getCitiesByName(String name){
        Map<String,List<CityDTO>> response = response = new TreeMap<>();
        List<CityDTO> citiesDto = new ArrayList<>();
        List<City> foundCities = locationRepository.findByName(name);
        foundCities.stream().map((city) -> {
            CityDTO cityDto = new CityDTO();
            cityDto.setName(city.getName() + (city.getAdmin1() != null ? ( ", " + city.getAdmin1()) : "") + (", " + city.getCountry()));
            cityDto.setLatitude(city.getLatitud());
            cityDto.setLongitud(city.getLongitud());
            cityDto.setScore(Math.round(((name.length() * 1D )/ city.getName().length())*100.0)/100.0);
            return cityDto;
        }).forEachOrdered((cityDto) -> {
            citiesDto.add(cityDto);
        });
        response.put("suggestions", citiesDto.stream()
        .sorted(Comparator.comparingDouble(CityDTO::getScore).reversed())
        .collect(Collectors.toList()));
        
        return response;
    }
    
}
