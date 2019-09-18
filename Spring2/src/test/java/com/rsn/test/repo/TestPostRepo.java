package com.rsn.test.repo;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsn.repository.PostRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:db.xml")
@Transactional
public class TestPostRepo {

	@Resource(name = "postRepo")
    private PostRepo postRepo;
	
	@Test
	public void recordCount() {
		assertEquals(postRepo.selectAll().size(), 5);
	}
	
	@Test
	public void getPostById() {
		assertEquals((long) postRepo.selectById(1).getPost_id(), (long) 1);
	}

	@Test
	public void getPostsByProfileId() {
		assertEquals(postRepo.selectByProfileId(11).size(), 2);
	}
	
	@Test
	public void getProfileByPostId() {
		assertEquals(postRepo.selectByProfileId(1).size(), 11);
	}
	////DUC operations to follow

	
}
