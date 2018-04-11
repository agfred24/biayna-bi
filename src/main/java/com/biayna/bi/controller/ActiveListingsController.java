package com.biayna.bi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ActiveListingsController {
	/*private PropertyListingsRQDTO propertyListings;
	
	public ActiveListingsController(PropertyListingsRQDTO propertyListings) {
		this.propertyListings = propertyListings;
	}*/
	
	@RequestMapping("/listings")
	public String getListings(Model model){
		
		//model.addAttribute("listings", PropertyListingsRQDTO.findAll());
		
		return "listings";
	}
}
