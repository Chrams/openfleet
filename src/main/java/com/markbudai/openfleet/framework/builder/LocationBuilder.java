package com.markbudai.openfleet.framework.builder;

import com.markbudai.openfleet.exception.EmptyParameterException;
import com.markbudai.openfleet.model.Location;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Mark on 2017. 03. 24..
 */
public class LocationBuilder {

    private Location loc;

    public LocationBuilder(){
        loc = new Location();
    }

    public LocationBuilder id(long id){
        loc.setId(id);
        return this;
    }

    public LocationBuilder country(String country){
        loc.setCountry(country);
        return this;
    }

    public LocationBuilder city(String city){
        loc.setCity(city);
        return this;
    }

    public LocationBuilder region(String region){
        loc.setRegion(region);
        return this;
    }

    public LocationBuilder street(String street){
        loc.setStreet(street);
        return this;
    }

    public LocationBuilder houseNo(String houseNo){
        loc.setHouseNo(houseNo);
        return this;
    }

    public LocationBuilder zipcode(String zipcode){
        loc.setZipcode(zipcode);
        return this;
    }

    public Location build(){
        return loc;
    }

    public void reset(){
        loc = new Location();
    }

    public static Location buildFromWebRequest(WebRequest request){
        Location loc = new Location();
        if(!(request.getParameter("id").isEmpty())){
            loc.setId(Long.parseLong(request.getParameter("id")));
        }
        if(request.getParameter("country").isEmpty()){
            throw new EmptyParameterException("country");
        }
        loc.setCountry(request.getParameter("country"));
        if(request.getParameter("city").isEmpty()){
            throw new EmptyParameterException("city");
        }
        loc.setCity(request.getParameter("city"));
        if(request.getParameter("region").isEmpty()){
            throw new EmptyParameterException("region");
        }
        loc.setRegion(request.getParameter("region"));
        if(request.getParameter("street").isEmpty()){
            throw new EmptyParameterException("street");
        }
        loc.setStreet(request.getParameter("street"));
        if(request.getParameter("houseNo").isEmpty()){
            throw new EmptyParameterException("houseNo");
        }
        loc.setHouseNo(request.getParameter("houseNo"));
        if(request.getParameter("zipcode").isEmpty()){
            throw new EmptyParameterException("zipcode");
        }
        loc.setZipcode(request.getParameter("zipcode"));
        return loc;
    }
}
