package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rsn.entity.Profile;

@Repository("profileRepo")
@Transactional

public class ProfileRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
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
	
	
	public String savePhoto(String username) {
		Profile profile = selectByUsername(username);
		String photo;
		do {
			photo = RandomStringUtils.random(15, true, true);
		} while ( photoExists(photo) );
		
		profile.setPhoto(photo);
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
	
	
	public Profile selectByUsername(String username) {
		return (Profile) sessionFactory
				.getCurrentSession()
				.createQuery("from Profile p where p.username = :un")
				.setParameter("un", username)
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
