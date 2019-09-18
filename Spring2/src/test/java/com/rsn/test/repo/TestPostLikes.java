package com.rsn.test.repo;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsn.repository.CommentLikesRepo;
import com.rsn.repository.PostLikesRepo;
import com.rsn.repository.PostRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:db.xml")
@Transactional
public class TestPostLikes {

	@Resource(name = "postLikesRepo")
    private PostLikesRepo postLikesRepo;
	
	@Test
	public void recordCount() {
		assertEquals(postLikesRepo.selectAll().size(), 17);
	}
	
	@Test
	public void getProfileByPostLikeId() {
		assertEquals(postLikesRepo.selectById(7).getProfile().getUser_id(), 9);
	}

	@Test
	public void getPostLikesOfProfileId() {
		assertEquals(postLikesRepo.selectPostLikesByProfileId(9).size(), 2);
	}
	
	////DUC operations to follow

}
