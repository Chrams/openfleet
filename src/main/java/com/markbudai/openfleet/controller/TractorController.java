package com.markbudai.openfleet.controller;


import com.markbudai.openfleet.services.TractorService;
import com.markbudai.openfleet.framework.builder.TractorBuilder;
import com.markbudai.openfleet.model.Tractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Mark on 2017. 04. 19..
 */
@Controller
public class TractorController {

    private TractorService tractorService;

    private static String viewPrefix = "tractor/";

    private static Logger logger = LoggerFactory.getLogger(TractorController.class);

    @Autowired
    public TractorController(TractorService provider){
        this.tractorService = provider;
    }

    @RequestMapping("/tractors/list")
    public String listTractors(Model model){
        model.addAttribute("path","/tractors/list");
        model.addAttribute("title","Tractors");
        model.addAttribute("tractorList", tractorService.getAllTractors());
        model.addAttribute("supervisionList", tractorService.getSupervisionList());
        return viewPrefix+"listTractors";
    }

    @RequestMapping("/tractor")
    public String getDetails(@RequestParam("id") long id, Model model){
        model.addAttribute("path","/tractor");
        model.addAttribute("title","Tractor Details");
        model.addAttribute("tractor", tractorService.getTractorById(id));
        return viewPrefix+"tractorDetails";
    }

    @RequestMapping("/tractor/delete")
    public String sellTractor(@RequestParam("id") long id, Model model){
        tractorService.sellTractor(id);
        return listTractors(model);
    }

    @PostMapping("/tractor/add")
    public String addTractor(Model model, WebRequest request){
        logger.debug("Starting tractor adding method.");
        logger.debug("Id is: {}",request.getParameter("id"));
        if(request.getParameter("id").isEmpty()){
            Tractor t = TractorBuilder.buildFromWebRequest(request);
            tractorService.addTractor(t);
        } else {
            Tractor t = TractorBuilder.buildFromWebRequest(request);
            tractorService.updateTractor(t);
        }
        return listTractors(model);
    }

    @RequestMapping("/tractor/new")
    public String newTractor(Model model){
        model.addAttribute("title","Tractor Form");
        return viewPrefix+"tractorDetails";
    }
}
