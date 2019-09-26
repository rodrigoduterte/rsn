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
import com.rsn.entity.PostLikes;

/**
 * @author Gabriel Ferrer.
 * A Repo that accesses methods that modify PostRepo in the database
 * This Repo can be also accessed by any controllers 
 */
@Repository("postRepo")
@Transactional
public class PostRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	public PostRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public long insert(Posts post) {
		return (long) sessionFactory.getCurrentSession().save(post);
	}
	
	public void update(Posts post) {
		sessionFactory.getCurrentSession().update(post);
	}
	
	public void delete(Posts post) {
		sessionFactory.getCurrentSession().delete(post);
	}
	
	public Posts selectById(long id) {
		return sessionFactory.getCurrentSession().get(Posts.class, id);
	}
	
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	public void merge(Posts post) {
		sessionFactory.getCurrentSession().merge(post);
	}
	
	public void evict(Posts post) {
		sessionFactory.getCurrentSession().evict(post);
	}
	
//	public boolean exists(Posts post) {
//		String hql = "";
//		if (validator.isEmail(emailOrUsername)) {
//			hql = "from Profile where email = :eu";
//		} else {
//			hql = "from Profile where username = :eu";
//		}
//		
//		try {
//			sessionFactory.getCurrentSession()
//					.createQuery
//					("from Posts where ", Profile.class)				
//					.setParameter("eu", emailOrUsername.toLowerCase())
//					.getSingleResult();
//		} catch (NoResultException e) {
//			return false;
//		}
//		return true;
//	}
	
	
	public List<Posts> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Posts order by post_date desc", Posts.class).list();
	}
	
	public List<Posts> selectByProfileId(int id) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Posts p where p.profile.user_id = :id order by post_date desc")
				.setParameter("id", id)
				.list();
	}
	
	public String createPhotoName() {
		String photo;
		do {
			photo = RandomStringUtils.random(20, true, true);
		} while ( photoExists(photo) );
		return photo;
	}
	
	public boolean photoExists(String photo) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Posts where photo = '" + photo + "'")
				.setMaxResults(1).uniqueResult() != null;
	}
	
	public List<Posts> selectLatestPosts(Long postId) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Posts p where p.post_id > :postId order by post_date desc")
				.setParameter("postId", postId)
				.list();
	}
	
	
}
