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
		return sessionFactory.getCurrentSession().createQuery("from PostLikes", PostLikes.class).list();
	}
	
	public List<PostLikes> selectPostLikesByProfileId(int id) {
		return sessionFactory.getCurrentSession()
				.createQuery("from PostLikes pl where pl.profile.user_id = :id")
				.setParameter("id", id)
				.list();
	}
	
	
}
