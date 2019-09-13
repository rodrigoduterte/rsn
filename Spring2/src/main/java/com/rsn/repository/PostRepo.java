package com.rsn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rsn.entity.Post;
import com.rsn.entity.PostLikes;

@Repository("postRepo")
@Transactional
public class PostRepo {
	@Autowired
	private SessionFactory sessionFactory;
	
	public PostRepo() {
		// TODO Auto-generated constructor stub
	}
	
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	public void close() {
		sessionFactory.getCurrentSession().close();
	}
	
	public void evict(Post post) {
		sessionFactory.getCurrentSession().evict(post);
	}
	
	public long insert(Post post) {
		return (long) sessionFactory.getCurrentSession().save(post);
	}
	
	public void update(Post post) {
		sessionFactory.getCurrentSession().update(post);
	}
	
	public void delete(Post post) {
		sessionFactory.getCurrentSession().delete(post);
	}
	
	public Post selectById(long id) {
		return sessionFactory.getCurrentSession().get(Post.class, id);
	}
	
	public List<Post> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from Post", Post.class).list();
	}
	
	
}
