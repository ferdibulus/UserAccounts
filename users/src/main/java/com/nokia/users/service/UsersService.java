package com.nokia.users.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.nokia.users.model.User;
import com.user.dao.UsersDao;

@Service
public class UsersService {

	public ArrayList<User> test() {
		UsersDao db = new UsersDao();
		ArrayList<String[]> users = db.getUsers();
		ArrayList<User> temp = new ArrayList<User>();
		
		Long id;
		String name;
		Long phone;
		String email;
		String address;
		String country;
		String department;
		
		for (String[] strings : users) {
		   int c=0;
		   id = Long.parseLong(strings[c++]);
		   name = strings[c++];
		   phone = Long.parseLong(strings[c++]);
		   email = strings[c++];
		   address = strings[c++];
		   country = strings[c++];
		   department = strings[c++];
		   
		   User user = new User(id,name,phone,email,address,country,department);
		 
		   temp.add(user);
		}
		
		return temp;
	}
	
	public boolean InsertUser(Long id, String username, Long userphone, String useremail, String useraddress, String usercountry, String userdept) {
		UsersDao db = new UsersDao();
		return db.insertUser(id,username,userphone,useremail,useraddress,usercountry,userdept);
	}
	
	public boolean UpdateUser(Long id, String username, Long userphone, String useremail, String useraddress, String usercountry, String userdept) {
		UsersDao db = new UsersDao();
		return db.updateUser(id,username,userphone,useremail,useraddress,usercountry,userdept);
	}
	
	public boolean DeleteUser(Long id) {
		UsersDao db = new UsersDao();
		return db.deleteUser(id);
	}

}
