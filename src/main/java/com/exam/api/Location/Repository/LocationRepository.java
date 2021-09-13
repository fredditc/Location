/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.api.Location.Repository;

import com.exam.api.Location.Controller.LocationController;
import com.exam.api.Location.Entity.City;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Freddy
 */
@Repository
public class LocationRepository {
    
    @Value("${URL_ARCHIVE}")
    private String URL_ARCHIVE;
    
    private List<City> cities;
    
    public void preLoadInformation() throws IOException{
        InputStream excel = null;
        this.cities = new ArrayList<>();
        try{
            excel = new FileInputStream(new File(System.getProperty("user.dir") + URL_ARCHIVE));
            HSSFWorkbook workbook = new HSSFWorkbook(excel);
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row;
            int rows = sheet.getLastRowNum();
            Iterator itr = sheet.iterator();
            for (int r = 1; r <= rows; r++) {
                City city = new City();
                row = sheet.getRow(r);
                if(row != null){
                    city.setId((int) row.getCell(0).getNumericCellValue());
                    city.setName(row.getCell(1).getStringCellValue());
                    city.setAscii(row.getCell(2).getStringCellValue());
                    if(!row.getCell(3).getStringCellValue().isEmpty()){
                        List<String> altNames = new ArrayList<>();
                        for(String altName : row.getCell(3).getStringCellValue().split(",")){
                            altNames.add(altName);
                        }
                        city.setAltNames(altNames);
                    }
                    city.setLatitud(String.valueOf(row.getCell(4).getNumericCellValue()));
                    city.setLongitud(String.valueOf(row.getCell(5).getNumericCellValue()));
                    city.setfClass(row.getCell(6).getStringCellValue());
                    city.setfCode(row.getCell(7).getStringCellValue());
                    city.setCountry(row.getCell(8).getStringCellValue());
                    city.setAdmin1(row.getCell(10).getCellType() == row.getCell(10).getCellType().STRING ? row.getCell(10).getStringCellValue() : null);
                    this.cities.add(city);
                    
                }
            }
        }catch (FileNotFoundException fileNotFoundException) {
            Logger.getLogger(LocationRepository.class.getName()).log(Level.SEVERE, null, fileNotFoundException);
        } catch (IOException ex) {
            Logger.getLogger(LocationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                excel.close();
            } catch (IOException ex) {
                Logger.getLogger(LocationRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<City> findByLatitudeAndLongitud(String latitude, String longitud){
        List<City> cities = new ArrayList<>();
        try{   
            if(this.cities.size() > 0){
                cities = this.cities.stream().filter(city -> city.getLatitud().equals(latitude) && city.getLongitud().equals(longitud))
                    .collect(Collectors.toList());
            }else{
                throw new Exception("ERROR: The information cannot be loaded.");
            }
        }catch (Exception ex) {
            Logger.getLogger(LocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cities;
    }
    
    public List<City> findByName(String name){
        List<City> cities = new ArrayList<>();
        List<City> citiesAltNames = new ArrayList<>();
        try{   
            if(this.cities.size() > 0){
                cities = this.cities.stream().filter(city -> city.getAltNames() == null && city.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
                
                this.cities.stream().filter(city -> city.getAltNames() != null).forEach(s -> {
                    List <String> found = s.getAltNames().stream().filter(alt -> alt.toLowerCase().contains(name.toLowerCase()))
                            .collect(Collectors.toList());
                    if(found.size()>0){
                        citiesAltNames.add(s);
                    }
                });
                cities.addAll(citiesAltNames);
            }else{
                throw new Exception("ERROR: The information by name cannot be loaded.");
            }
        }catch (Exception ex) {
            Logger.getLogger(LocationRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cities;
    }
    
}
