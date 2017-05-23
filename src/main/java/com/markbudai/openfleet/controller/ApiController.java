package com.markbudai.openfleet.controller;

import com.markbudai.openfleet.model.*;
import com.markbudai.openfleet.pojo.Badge;
import com.markbudai.openfleet.pojo.PaymentDetail;
import com.markbudai.openfleet.pojo.SamplePieData;
import com.markbudai.openfleet.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

/**
 * Created by Mark on 2017. 04. 29..
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    private TransferCostService provider;
    private TractorService tractorService;
    private TrailerService trailerService;
    private EmployeeService employeeService;
    private LocationService locationService;
    private TransportService transportService;
    private PaymentService paymentService;

    @Autowired
    public ApiController(TractorService tractorService, TrailerService trailerService,
                         EmployeeService employeeService, LocationService locationService,
                         TransportService transportService, PaymentService paymentService){
        this.employeeService = employeeService;
        this.trailerService = trailerService;
        this.tractorService = tractorService;
        this.locationService = locationService;
        this.transportService = transportService;
        this.paymentService = paymentService;

    }

    @RequestMapping(value = "/drivers", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SamplePieData> pieData() {
        List<SamplePieData> l = new ArrayList<>();
        l.add(new SamplePieData("Driver 1",300));
        l.add(new SamplePieData("Driver 2",200));
        l.add(new SamplePieData("Driver 3",1000));

        return l;
    }

    @RequestMapping(value = "/tractors", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Tractor> tractors(){
        return tractorService.getAllTractors();
    }

    @RequestMapping(value = "/badges", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Badge badge(){
        Badge badges = new Badge();
        badges.setLocation_count(this.allLocations().size());
        badges.setEmployee_count(this.employees().size());
        badges.setTrailer_count(this.trailers().size());
        badges.setTractor_count(this.tractors().size());
        badges.setJob_count(this.transportService.getAllTransports().size());
        return badges;
    }

    @RequestMapping(value = "/trailers", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Trailer> trailers(){
        return trailerService.getAllTrailers();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Employee> employees(){
        return employeeService.getAllEmployees();
    }


    @RequestMapping(value = "/locations", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Location> allLocations(){
        List<Location> locations = locationService.getAllLocations();
        return locations;
    }

    @RequestMapping(value = "/paymentDetails",method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PaymentDetail> getPaymentsForEmployee(@RequestParam("id") long id){
        Employee employee = employeeService.getEmployeeById(id);
        List<PaymentDetail> paymentDetails = paymentService.getAllPaymentsForEmployee(employee);
        if(paymentDetails.isEmpty()){
            logger.debug("No payment details created for employee with id: {}",id);
            return null;
        } else {
            return paymentDetails;
        }
    }



}
