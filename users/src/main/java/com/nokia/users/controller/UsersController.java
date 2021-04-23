package com.nokia.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UsersController {
	
	@SuppressWarnings("unused")
	private final static String ERROR_PATH = "/error";
	
	@GetMapping("Home")
	public String getHome() {
		
        return "home";
    }
	
	@GetMapping("Accounts")
	public String get() {
		
        return "index";
    }

}
