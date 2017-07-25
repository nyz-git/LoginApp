package com.stackroute.activitystream.ActivityStreamBackend.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.activitystream.ActivityStreamBackend.model.User;


@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDAO {

	Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;

	}

	public User registerUser(User user) {
		// TODO Auto-generated method stub

		try {
			sessionFactory.getCurrentSession().save(user);
			return user;
		} catch (Exception e) {
			log.debug(e.toString());
			log.debug("User Registration Failed");
			return null;
		}
	}

	public User validateUser(User user) {
		// TODO Auto-generated method stub

		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("from User where userName = ? and password = ?");
			query.setParameter(0, user.getUserName());
			query.setParameter(1, user.getPassword());
			
			
			user = (User) query.uniqueResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<User> user = sessionFactory.getCurrentSession().createQuery("from User").list();
		return user;
	}

}
