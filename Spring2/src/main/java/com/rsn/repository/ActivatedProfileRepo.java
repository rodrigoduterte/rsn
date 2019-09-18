package com.rsn.repository;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.RandomStringUtils;

import com.rsn.entity.ActivatedProfile;
import com.rsn.entity.Profile;

@Repository("activatedRepo")
@Transactional
public class ActivatedProfileRepo {
	@Autowired
	private SessionFactory sessionFactory;

	public ActivatedProfileRepo() {
		// TODO Auto-generated constructor stub
	}

	public boolean exists(String word) {
		return sessionFactory.getCurrentSession()
				.createQuery("from ActivatedProfile where activeId = '" + word + "'")
				.setMaxResults(1).uniqueResult() != null;
	}

	public String createNotExists(Profile user, Boolean activated) {
		String word = "";

		do {
			word = RandomStringUtils.random(10, true, true);
		} while ( exists(word) );

		
		sessionFactory.getCurrentSession().save(new ActivatedProfile(word, activated, user));
		return word;

	}
}
