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
	
	public void evict(PostLikes like) {
		sessionFactory.getCurrentSession().evict(like);
	}
	
	public PostLikes selectById(long id) {
		return sessionFactory.getCurrentSession().get(PostLikes.class, id);
	}
	
	
	
	public List<PostLikes> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from PostLikes", PostLikes.class).list();
	}
	
	public String toggle(PostLikes like) {
		PostLikes postLikes = selectById(like.getLike_id());
		boolean liked = postLikes.isLiked();
			
		postLikes.setLiked(!liked);
		update(postLikes);
		
		return !liked == true ? "Liked" : "Unliked";
	}
	
	public List<PostLikes> selectPostLikesByProfileId(int id) {
		return sessionFactory.getCurrentSession()
				.createQuery("from PostLikes pl where pl.profile.user_id = :id")
				.setParameter("id", id)
				.list();
	}
	
	public List<PostLikes> selectPostLikesByPostId(long id) {
		return sessionFactory.getCurrentSession()
				.createQuery("from PostLikes pl where pl.posts.post_id = :id")
				.setParameter("id", id)
				.list();
	}
	
	public PostLikes selectSpecificLike(String username, long postId) {
		return (PostLikes) sessionFactory.getCurrentSession()
				.createQuery("from PostLikes pl where pl.posts.post_id = :id and pl.profile.username = :u ")
				.setParameter("id", postId)
				.setParameter("u", username)
				.getSingleResult();
	}
	
	public boolean exists(String username, long postId) {
		try {
			return selectSpecificLike(username, postId) != null;
		} catch (Exception e) {
			return false;
		}
		
	}
}
