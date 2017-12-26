package com.bpjoshi.tradevalidator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bpjoshi.tradevalidator.config.ClassInfo;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Controller
@ClassInfo(summary = "This class redirects '/' URL to the HTML5 UI page where trade information can be validated")
public class PageController {
	@GetMapping(value="/")
    public String getHomePage(){
        return "index";
    }
}
