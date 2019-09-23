package com.rsn.test.repo;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsn.repository.CommentRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:db.xml")
@Transactional
public class TestCommentRepo {

	@Resource(name = "commentRepo")
    private CommentRepo commentRepo;
	
	@Test
	public void recordCount() {
		assertEquals(commentRepo.selectAll().size(), 5);
	}
	
	@Test
	public void getPostById() {
		assertEquals((long) commentRepo.selectById(1).getComment_id(), (long) 1);
	}

	////DUC operations to follow

}
