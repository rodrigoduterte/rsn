package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.Comments;
import com.rsn.entity.CommentLikes;


/**
 * @author Gabriel Ferrer.
 * A Repo that accesses methods that modify CommentRepo in the database
 * This Repo can be also accessed by any controllers 
 */
@Repository("commentRepo")
@Transactional
public class CommentRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CommentRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public long insert(Comments comment) {
		return (long) sessionFactory.getCurrentSession().save(comment);
	}
	
	public void update(Comments comment) {
		sessionFactory.getCurrentSession().update(comment);
	}
	
	public void delete(Comments comment) {
		sessionFactory.getCurrentSession().delete(comment);
	}
	
	public Comments selectById(long id) {
		return sessionFactory.getCurrentSession().get(Comments.class, id);
	}
	
	public List<Comments> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Comments", Comments.class).list();
	}
	
	
}
