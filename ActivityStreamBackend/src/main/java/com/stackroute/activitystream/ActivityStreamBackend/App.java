package com.stackroute.activitystream.ActivityStreamBackend;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		UserDetails userDetails = new UserDetails();
		userDetails.setId(101);
		userDetails.setFirstName("Niyaz");
		userDetails.setLastName("Ahmed");
		userDetails.setEmail("niyaz.ahmed@niit.com");
		userDetails.setPassword("nyz");

		Configuration configuration = new Configuration().configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.save(userDetails);
		transaction.commit();
	}
}
