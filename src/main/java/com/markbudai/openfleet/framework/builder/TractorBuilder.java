package com.markbudai.openfleet.framework.builder;

import com.markbudai.openfleet.exception.EmptyParameterException;
import com.markbudai.openfleet.model.Tractor;
import org.apache.tomcat.jni.Local;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

/**
 * Created by Mark on 2017. 05. 07..
 */
public class TractorBuilder {

    public static Tractor buildFromWebRequest(WebRequest request){
        Tractor tractor = new Tractor();
        if(!request.getParameter("id").isEmpty()){
            tractor.setId(Long.parseLong(request.getParameter("id")));
        }
        if(request.getParameter("type").isEmpty()){
            throw new EmptyParameterException("type");
        }
        tractor.setType(request.getParameter("type"));
        if(request.getParameter("manufacturer").isEmpty()){
            throw new EmptyParameterException("manufacturer");
        }
        tractor.setManufacturer(request.getParameter("manufacturer"));
        if(request.getParameter("date_of_manufacture").isEmpty()){
            throw new EmptyParameterException("date_of_manufacture");
        }
        tractor.setDate_of_manufacture(LocalDate.parse(request.getParameter("date_of_manufacture")));
        if(request.getParameter("date_of_acquire").isEmpty()){
            throw new EmptyParameterException("date_of_acquire");
        }
        tractor.setDate_of_acquire(LocalDate.parse(request.getParameter("date_of_acquire")));
        if(request.getParameter("date_of_supervision").isEmpty()){
            throw new EmptyParameterException("date_of_supervision");
        }
        tractor.setDate_of_supervision(LocalDate.parse(request.getParameter("date_of_supervision")));
        if(request.getParameter("plate_number").isEmpty()){
            throw new EmptyParameterException("plate_number");
        }
        tractor.setPlate_number(request.getParameter("plate_number"));
        if(request.getParameter("chassis_number").isEmpty()){
            throw new EmptyParameterException("chassis_number");
        }
        tractor.setChassis_number(request.getParameter("chassis_number"));
        if(request.getParameter("fuel_norm").isEmpty()){
            throw new EmptyParameterException("fuel_norm");
        }
        tractor.setFuel_norm(Double.parseDouble(request.getParameter("fuel_norm")));
        if(request.getParameter("weight").isEmpty()){
            throw new EmptyParameterException("weight");
        }
        tractor.setWeight(Long.parseLong(request.getParameter("weight")));
        if(request.getParameter("max_weight").isEmpty()){
            throw new EmptyParameterException("max_weight");
        }
        tractor.setMax_weight(Long.parseLong(request.getParameter("max_weight")));
        return tractor;
    }
}
