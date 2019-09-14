package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
				("from Profile p where (lower(p.firstName) like lower(:n) "
						+ "OR lower(p.middleName) like lower(:n) "
						+ "OR lower(p.lastName) like lower(:n))", 
				Profile.class)
				.setParameter("n", name)
				.list();
	}
	
	public List<Profile> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Profile", Profile.class).list();
	}
}
