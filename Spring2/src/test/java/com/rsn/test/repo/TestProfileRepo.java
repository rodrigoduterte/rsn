package com.rsn.test.repo;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsn.entity.Profile;
import com.rsn.repository.ProfileRepo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:localContext.xml")
@Transactional
public class TestProfileRepo {
	
	@Resource(name = "profileRepo")
    private ProfileRepo profileRepo;
	
	@Test
	public void recordCount() {
		assertEquals(profileRepo.selectAll().size(),31);
	}
	
	@Test
	public void getUser() {
		assertEquals(profileRepo.selectByUsername("aattle2").getFirstName(),"Arther");
	}

	@Test
	public void selectedAllByName() {
		assertEquals(profileRepo.selectAllByName("Ells").size(),2);
	}
	
	@Test
	public void createdProfile() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
		Profile profile = new Profile("gabeferr","gabrielferrergtf@gmail.com","Gabriel","Tapawan","Ferrer",
				formatter.parse("6/16/1989"),"abcde1234","Red","Orlando","Never Married","Male",
				"Secured fault-tolerant groupware","","Software Developer");
		assertEquals(profileRepo.insert(profile) instanceof Integer, true);
	}
	
	@Test
	public void deletedProfile() {
		Profile bweyt = profileRepo.selectByUsername("bweyt");
		profileRepo.delete(bweyt);
		assertEquals(profileRepo.selectAll().size() < 30, true);
	}
	
	@Test
	public void updatedProfile() {
		Profile eoggerf = profileRepo.selectByUsername("eoggerf");
		eoggerf.setFirstName("Logger");
		assertEquals(profileRepo.selectByUsername("eoggerf").getFirstName().equals("Logger"), true);
	}

}
