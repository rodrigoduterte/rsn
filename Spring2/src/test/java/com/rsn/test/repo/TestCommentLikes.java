package com.rsn.test.repo;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsn.repository.CommentLikesRepo;
import com.rsn.repository.CommentRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:db.xml")
@Transactional
public class TestCommentLikes {

	@Resource(name = "commentLikesRepo")
    private CommentLikesRepo commentLikesRepo;
	
	@Test
	public void recordCount() {
		assertEquals(commentLikesRepo.selectAll().size(), 13);
	}
	
	@Test
	public void getProfileBySelectingCommentLikeId() {
		assertEquals(commentLikesRepo.selectById(1).getProfile().getUser_id(), 6);
	}

	@Test
	public void getCommentLikesOfProfileId() {
		assertEquals(commentLikesRepo.selectByProfileId(6).size(), 2);
	}
	
	////DUC operations to follow

}
