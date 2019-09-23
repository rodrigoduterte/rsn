package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.Posts;
import com.rsn.entity.PostLikes;

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
	
	public List<Posts> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Posts", Posts.class).list();
	}
	
	public List<Posts> selectByProfileId(int id) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Posts p where p.profile.user_id = :id")
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
				.createQuery("from Posts p where p.post_id > :postId")
				.setParameter("postId", postId)
				.list();
	}
	
	
}
