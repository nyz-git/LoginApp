package com.stackroute.activitystream.ActivityStreamBackend.dao;

import java.util.List;

import com.stackroute.activitystream.ActivityStreamBackend.model.User;

public interface UserDAO {

	// for registering user
	public User registerUser(User user);

	// for validating user
	public User validateUser(User user);

	// for fetching a single user by its id
	public User getUserById(int id);

	// for fetching all user
	public List<User> getAllUsers();

}
