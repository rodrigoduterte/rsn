package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.Comment;
import com.rsn.entity.CommentLikes;

@Repository("commentRepo")
@Transactional
public class CommentRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CommentRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	public void close() {
		sessionFactory.getCurrentSession().close();
	}
	
	public void evict(Comment comment) {
		sessionFactory.getCurrentSession().evict(comment);
	}
	
	public long insert(Comment comment) {
		return (long) sessionFactory.getCurrentSession().save(comment);
	}
	
	public void update(Comment comment) {
		sessionFactory.getCurrentSession().update(comment);
	}
	
	public void delete(Comment comment) {
		sessionFactory.getCurrentSession().delete(comment);
	}
	
	public Comment selectById(long id) {
		return sessionFactory.getCurrentSession().get(Comment.class, id);
	}
	
	public List<Comment> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Comment", Comment.class).list();
	}
	
	
}
