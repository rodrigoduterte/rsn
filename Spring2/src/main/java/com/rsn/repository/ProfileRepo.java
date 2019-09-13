package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.Post;
import com.rsn.entity.Profile;

@Repository("profileRepo")
@Transactional
public class ProfileRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProfileRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	public void close() {
		sessionFactory.getCurrentSession().close();
	}
	
	public void evict(Profile profile) {
		sessionFactory.getCurrentSession().evict(profile);
	}
	
	public void insert(Profile profile) {
		sessionFactory.getCurrentSession().save(profile);
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
				("from Profile u where (lower(u.firstName) like :n OR lower(u.middleName) like :n OR lower(u.lastName) like :n)", 
				Profile.class)
				.setParameter("n", name)
				.list();
	}
	
	public List<Profile> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Profile", Profile.class).list();
	}
}
