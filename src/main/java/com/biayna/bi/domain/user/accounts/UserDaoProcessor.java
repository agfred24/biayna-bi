package com.biayna.bi.domain.user.accounts;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.biayna.bi.common.helper.RegistrationLoginHelper;
import com.biayna.bi.common.utility.ReadConfiguration;

public class UserDaoProcessor {
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("biayna_bi");
	private EntityManager entityManager = factory.createEntityManager();;
	private EntityTransaction tx = entityManager.getTransaction();
	private static Logger logger = LogManager.getLogger();
	
	public RegistrationErrors registerUser (UserVO user) {	
		RegistrationErrors errors = null;
		ReadConfiguration objPropertiesFile = new ReadConfiguration(); 
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		short loginAttempts = 0;
		
		try {
			
			UserAccountDao existingUserAccountDao = entityManager.find(UserAccountDao.class, user.getEmail());
			if (existingUserAccountDao != null) {
				errors = new RegistrationErrors();
				errors.setDatabaseError(objPropertiesFile.readKey("error.properties", "registration.duplicate"));
				return errors;
			}
			
			RoleDao userRole = new RoleDao(user.getRoleId(), null);			
			UserDao userDao = new UserDao(user.getFirstName(), user.getLastName(), userRole);
					
			UserAccountDao userAccountDao = new UserAccountDao(user.getEmail(), user.getPassword(), 
					user.isEnabled(), loginAttempts, timestamp, null, timestamp, userDao);
			tx.begin();
			entityManager.persist(userAccountDao);
			tx.commit();

		} catch (PersistenceException e) {
			System.out.println(e);
			errors = new RegistrationErrors();
			errors.setDatabaseError(objPropertiesFile.readKey("error.properties", "registration.dbNotAvailable"));
		} finally {
			entityManager.clear();
			entityManager.close();
			factory.close();
		}
		return errors;		
	}

	public User validateUser(LoginVO login) {
		User userDTO = null;

		try {
			UserAccountDao userAccountDao = entityManager.find(UserAccountDao.class, login.getEmail());				
			if (userAccountDao!=null) {
				if (RegistrationLoginHelper.isCorrectPassword(login.getPassword(), userAccountDao.getPassword())){
					
					tx.begin();
					userAccountDao.setLoginAttempts((short)1);
					userAccountDao.setLastLogin(new Timestamp(System.currentTimeMillis()));
					tx.commit();
					
					Role role = new Role(userAccountDao.getUserDao().getRole().getRoleId(), userAccountDao.getUserDao().getRole().getRole());
					userDTO = new User(userAccountDao.getEmail(), userAccountDao.getUserDao().getFirstname(), userAccountDao.getUserDao().getLastname(), role);
				}
			}
			
		} catch (PersistenceException e) {
			userDTO = null;
		} finally {
			entityManager.clear();
			entityManager.close();
			factory.close();
		}
		return userDTO;
	}
		
}
