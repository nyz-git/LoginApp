package com.stackroute.activitystream.ActivityStreamBackend.test;


import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.activitystream.ActivityStreamBackend.dao.UserDAO;
import com.stackroute.activitystream.ActivityStreamBackend.model.User;


public class UserCrudTest {

	@Autowired
	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static User user;

	@Autowired
	private static UserDAO userDAO;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream.ActivityStreamBackend");
		context.refresh();
		user = (User) context.getBean(User.class);
		userDAO = (UserDAO) context.getBean("userDAO");

	}

	/*@Test
	public void userRegisterTesCases() {
		//user.setUid(2);
		user.setEmail("aaaaaxyz@niit.com");
		user.setFirstName("XaaaaYZ");
		user.setLastName("aaaNIIT");
		user.setPhoneNumber("9898989898");
		user.setPassword("aaxyz");
		user.setUserName("aaxyz");
		assertEquals("User Registered Sucessfully", User.class,userDAO.registerUser(user).getClass());
	}*/

	/*@Test
	public void userValidateTest() {
		user.setUserName("niyaz");
		user.setPassword("nyz");
		assertEquals("User Validated", User.class, userDAO.validateUser(user).getClass());
	}*/
	
	/*@Test
	public void getUserByIdTest()
	{
		User user=userDAO.getUserById(1);
		assertEquals("Match Found","Niyaz",user.getFirstName());
		displayUserById("Single User",user);
	}*/
	
	@Test
	public void getAllUser() {
		
		
		int usersCount = userDAO.getAllUsers().size();
		
		List<User> users=userDAO.getAllUsers();
		assertEquals("User Data", usersCount, userDAO.getAllUsers().size());
		displayAllUsers("List of All Registered Users",users);
		
		
	}
	private void displayUserById(String testCaseName, User user) {
		System.out.println(testCaseName);
		System.out.println(user.getFirstName());
		
		
	}
	
	public void displayAllUsers(String testCaseName,List<User> users)
	{
		System.out.println(testCaseName);
		
		Iterator i= users.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		
		
	}
}
