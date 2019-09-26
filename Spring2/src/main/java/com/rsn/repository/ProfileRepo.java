package com.rsn.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.Posts;
import com.rsn.entity.Profile;
import com.rsn.service.Validator;

/**
 * @author Gabriel Ferrer.
 * A Repo that accesses methods that modify ProfileRepo in the database
 * This Repo can be also accessed by any controllers 
 */
@Repository("profileRepo")
@Transactional
public class ProfileRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Validator validator;
	
	public ProfileRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer insert(Profile profile) {
		return (Integer) sessionFactory.getCurrentSession().save(profile);
	}
	
	public void update(Profile profile) {
		sessionFactory.getCurrentSession().update(profile);
	}
	
	public void delete(Profile profile) {
		sessionFactory.getCurrentSession().delete(profile);
	}
	
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	public void merge(Profile profile) {
		sessionFactory.getCurrentSession().merge(profile);
	}
	
	public void evict(Profile profile) {
		sessionFactory.getCurrentSession().evict(profile);
	}
	
	
	public String savePhoto(String username) {
		Profile profile = selectByUsername(username);
		String photo;
		do {
			photo = RandomStringUtils.random(15, true, true);
		} while ( photoExists(photo) );
		
		profile.setPhoto(photo);
		update(profile);
		return photo;
	}
	
	public boolean photoExists(String photo) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Profile where photo = '" + photo + "'")
				.setMaxResults(1).uniqueResult() != null;
	}
	
	public String getPhoto(String username) {
		Profile profile = selectByUsername(username);
		
		return profile.getPhoto();
	}
	
	public boolean exists(String emailOrUsername) {
		String hql = "";
		if (validator.isEmail(emailOrUsername)) {
			hql = "from Profile where email = :eu";
		} else {
			hql = "from Profile where username = :eu";
		}
		
		try {
			sessionFactory.getCurrentSession()
					.createQuery
					(hql, Profile.class)				
					.setParameter("eu", emailOrUsername.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}
	
	public Profile selectByUsername(String username) {
		return (Profile) sessionFactory
				.getCurrentSession()
				.createQuery("from Profile p where p.username = :un")
				.setParameter("un", username)
				.getSingleResult(); //uniqueResult()
	}
	
	///added after EC2 deploy
	public Profile selectByEmail(String email) {
		return (Profile) sessionFactory
				.getCurrentSession()
				.createQuery("from Profile p where p.email = :em")
				.setParameter("em", email)
				.getSingleResult(); //uniqueResult()
	}
	
	public List<Profile> selectAllByName(String name) {
		return sessionFactory.getCurrentSession()
				.createQuery
				("from Profile p where (lower(p.firstName) like :n "
						+ "OR lower(p.middleName) like :n "
						+ "OR lower(p.lastName) like :n)", 
				Profile.class)
				.setParameter("n", name.toLowerCase() + "%")
				.list();
	}
	
	public List<Profile> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Profile", Profile.class).list();
	}
}
