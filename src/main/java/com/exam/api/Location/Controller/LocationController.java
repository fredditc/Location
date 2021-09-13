/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.api.Location.Controller;

import com.exam.api.Location.dto.CityDTO;
import com.exam.api.Location.Service.LocationService;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Freddy
 */
@RestController
@RequestMapping("/api")
public class LocationController {
    
    @Autowired
    LocationService locationService;
    
    @RequestMapping(path = "/suggestions", method = RequestMethod.GET)
    public Map<String,List<CityDTO>> getSuggestLocation( @RequestParam(required = true) String q, @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitud) { 
            Map<String,List<CityDTO>> response = new TreeMap<>();
        try {
            if(latitude != null && longitud != null){
                //Busqueda por coordernadas
                if(!latitude.isEmpty() && !longitud.isEmpty()){
                    response = locationService.getCitiesByCoordenate(q,latitude, longitud);
                }
            }else{
                //Busqueda por posibles nombres
                if(q.length()>0){
                    response = locationService.getCitiesByName(q);
                }else{
                    throw new Exception("Location is required.");
                }
            }
        }catch (Exception ex) {
            Logger.getLogger(LocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
   
}
