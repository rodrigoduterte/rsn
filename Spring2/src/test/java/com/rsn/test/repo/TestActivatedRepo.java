package com.rsn.test.repo;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsn.repository.ActivatedProfileRepo;
import com.rsn.repository.CommentLikesRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:db.xml")
@Transactional
public class TestActivatedRepo {

	@Resource(name = "activatedProfileRepo")
    private ActivatedProfileRepo activatedProfileRepo;
	
	@Test
	public void exists() {
		assertEquals(activatedProfileRepo.exists("abcdefg"), false);
	}

}
