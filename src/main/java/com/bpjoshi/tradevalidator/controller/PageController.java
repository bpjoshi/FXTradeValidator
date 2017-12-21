package com.bpjoshi.tradevalidator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Home for Testing
 */
@Controller
public class PageController {
	@GetMapping(value="/")
    public String getHomePage(){
        return "index";
    }
}
