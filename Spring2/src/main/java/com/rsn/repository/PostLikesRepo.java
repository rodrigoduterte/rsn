package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.CommentLikes;
import com.rsn.entity.PostLikes;

@Repository("postLikesRepo")
@Transactional
public class PostLikesRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	public PostLikesRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	public void close() {
		sessionFactory.getCurrentSession().close();
	}
	
	public void evict(PostLikes like) {
		sessionFactory.getCurrentSession().evict(like);
	}
	
	public void insert(PostLikes like) {
		sessionFactory.getCurrentSession().save(like);
	}
	
	public void update(PostLikes like) {
		sessionFactory.getCurrentSession().update(like);
	}
	
	public void delete(PostLikes like) {
		sessionFactory.getCurrentSession().delete(like);
	}
	
	public PostLikes selectById(long id) {
		return sessionFactory.getCurrentSession().get(PostLikes.class, id);
	}
	
	public List<PostLikes> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Like", PostLikes.class).list();
	}
	
	
}
