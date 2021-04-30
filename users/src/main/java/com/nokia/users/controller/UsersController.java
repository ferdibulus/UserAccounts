package com.nokia.users.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
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
	
	@PostMapping("/AddUser")
	public String postInsertUser(ModelMap model, HttpServletRequest request) {
		
		String idtemp=request.getParameter("id"); 
		Long id = Long.parseLong(idtemp);
		String username=request.getParameter("username"); 
		String userphonetemp=request.getParameter("userphone"); 
		Long userphone = Long.parseLong(userphonetemp);
		String useremail=request.getParameter("useremail"); 
		String useraddress=request.getParameter("useraddress"); 
		String usercountry=request.getParameter("usercountry"); 
		String userdept=request.getParameter("userdept"); 
		String msg="";
		boolean insertedUser=service.InsertUser(id,username,userphone,useremail,useraddress,usercountry,userdept);
		if(!insertedUser) {
			msg = "User inserted failed.";
		}else {
			msg = "User inserted succefsully.";
		}	
		
		 ArrayList<User> allAccounts = new ArrayList<User>();
         allAccounts = service.test();
         model.addAttribute("accounts", allAccounts);
         model.addAttribute("message", msg);
       return "index";
   }
	
	@PostMapping("/UpdateUser")
	public String postUpdateUser(ModelMap model, HttpServletRequest request) {
		String id=request.getParameter("id"); 
		String username=request.getParameter("username"); 
		String userphone=request.getParameter("userphone"); 
		String useremail=request.getParameter("useremail"); 
		String useraddress=request.getParameter("useraddress"); 
		String usercountry=request.getParameter("usercountry"); 
		String userdept=request.getParameter("userdept"); 
		
		
		
		
		 ArrayList<User> allAccounts = new ArrayList<User>();
         allAccounts = service.test();
         model.addAttribute("accounts", allAccounts);
       return "index";
   }
	
	@PostMapping("/DeleteUser")
	public String postDeleteUser(ModelMap model, HttpServletRequest request) {
		String id=request.getParameter("id"); 
		
		
		 ArrayList<User> allAccounts = new ArrayList<User>();
         allAccounts = service.test();
         model.addAttribute("accounts", allAccounts);
       return "index";
   }
}
