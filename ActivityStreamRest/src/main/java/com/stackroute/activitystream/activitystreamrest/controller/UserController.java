package com.stackroute.activitystream.activitystreamrest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stackroute.activitystream.ActivityStreamBackend.dao.UserDAO;
import com.stackroute.activitystream.ActivityStreamBackend.model.User;

@Controller
public class UserController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	
	
	public ResponseEntity insertUser(@RequestBody User user) {
		try {
			User newUser = userDAO.registerUser(user);
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session) {

		User user = (User) session.getAttribute("user"); // authentication
		if (user == null) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		} else {
			user = userDAO.getUserById(user.getUid());
			return new ResponseEntity(HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/get/alluser", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser() {
		List<User> users = userDAO.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);

		}

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User validUser = userDAO.validateUser(user);
		if (validUser == null) {
			System.out.println("No User Found");
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		} else {
			System.out.println("User Found");
			session.setAttribute("user", validUser);
			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<?> logOut(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

	}

}
