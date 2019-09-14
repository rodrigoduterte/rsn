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
		return sessionFactory.getCurrentSession().createQuery("from CommentLikes", CommentLikes.class).list();
	}
	
	public List<CommentLikes> selectByProfileId(int id) {
		return sessionFactory.getCurrentSession()
				.createQuery("from CommentLikes cl where cl.profile.user_id = :id")
				.setParameter("id", id)
				.list();
	}
}
