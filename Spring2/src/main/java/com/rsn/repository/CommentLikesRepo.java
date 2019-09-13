package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.CommentLikes;

@Repository("commentLikesRepo")
@Transactional
public class CommentLikesRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CommentLikesRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	public void close() {
		sessionFactory.getCurrentSession().close();
	}
	
	public void evict(CommentLikes like) {
		sessionFactory.getCurrentSession().evict(like);
	}
	
	public void insert(CommentLikes like) {
		sessionFactory.getCurrentSession().save(like);
	}
	
	public void update(CommentLikes like) {
		sessionFactory.getCurrentSession().update(like);
	}
	
	public void delete(CommentLikes like) {
		sessionFactory.getCurrentSession().delete(like);
	}
	
	public CommentLikes selectById(long id) {
		return sessionFactory.getCurrentSession().get(CommentLikes.class, id);
	}
	
	public List<CommentLikes> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Like", CommentLikes.class).list();
	}
	
	
}
