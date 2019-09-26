package com.rsn.repository;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.RandomStringUtils;

import com.rsn.entity.ActivatedProfile;
import com.rsn.entity.Profile;

/**
 * @author Gabriel Ferrer.
 * A Repo that accesses methods that modify ActivatedProfile in the database
 * This Repo can be also accessed by any controllers 
 */
@Repository("activatedProfileRepo")
@Transactional
public class ActivatedProfileRepo {
	@Autowired
	private SessionFactory sessionFactory;

	public ActivatedProfileRepo() {
	}

	public boolean exists(String word) {
		return sessionFactory.getCurrentSession()
				.createQuery("from ActivatedProfile where activeId = '" + word + "'")
				.setMaxResults(1).uniqueResult() != null;
	}

	public boolean isActivated(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("from ActivatedProfile a where a.profile.username = '" + username 
						+ "' and activated = true")
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
	
	public void update(ActivatedProfile activedProfile) {
		sessionFactory.getCurrentSession().update(activedProfile);
	}
	
	public void activate(String activeId) {
		ActivatedProfile activeProfile = 
				sessionFactory.getCurrentSession()
				.createQuery
				("from ActivatedProfile where activeId = '" + activeId + "'", 
						ActivatedProfile.class)
				.getSingleResult();
		activeProfile.setActivated(true);
		update(activeProfile);
	}
}
