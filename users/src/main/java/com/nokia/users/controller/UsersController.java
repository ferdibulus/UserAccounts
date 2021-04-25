package com.nokia.users.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.users.model.User;
import com.nokia.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	UsersService service;
	
	@SuppressWarnings("unused")
	private final static String ERROR_PATH = "/error";
	
	@GetMapping("Home")
	public String getHome() {

        return "home";
    }
	
	@GetMapping("Accounts")
	public String get(ModelMap model) {
		 ArrayList<User> allAccounts = new ArrayList<User>();
         allAccounts = service.test();
         model.addAttribute("accounts", allAccounts);
        return "index";
    }

}
