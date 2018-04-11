package com.biayna.bi.domain.user.accounts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDaoProcessor {
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	private static Logger logger = LogManager.getLogger();
	
	private static void begin() {
		factory = Persistence.createEntityManagerFactory("biayna_bi");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();		
	}
	
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.clear();
		entityManager.close();
	}
	

	public boolean registerUser (UserVO user) {		
		
		UserDao userDeo = new UserDao(user.getEmail(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getPhone());
		try {
			begin();
			entityManager.persist(userDeo);
			end();
		} catch (Exception e) {
			return false;
		} 
		return true;				
	}
	
	public UserVO validateUser(LoginVO login) {
		UserVO user = null;

		try {
			begin();

			String jpql = "Select u From UserDao u Where u.email=:email";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("email", login.getEmail());
			UserDao userDao = (UserDao) query.getSingleResult();
			user = new UserVO(userDao.getEmail(), userDao.getPassword(), userDao.getFirstname(), userDao.getLastname(), userDao.getPhone());
			end();
		} catch (Exception e) {
			return null;
		}
		return user;
	}
		
}
