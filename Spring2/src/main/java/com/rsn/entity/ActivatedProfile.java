package com.rsn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Gabriel Ferrer
 * A persisting model for saving activated profile once a customer is registered
 * changes to activated once the customer press the Activate my account
 */
@Entity
@Table(name="ACTIVATEDPROFILE")
public class ActivatedProfile {

    @Id
    private String activeId;

    @Basic
    private Boolean activated;

    @OneToOne
    private Profile profile;

    public ActivatedProfile() {
		super();
	}

	public ActivatedProfile(String activeId, Boolean activated, Profile profile) {
		super();
		this.activeId = activeId;
		this.activated = activated;
		this.profile = profile;
	}

	public String getActiveId() {
        return activeId;
    }

    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

	@Override
	public String toString() {
		return "ActivatedProfile [activeId=" + activeId + ", activated=" + activated + ", profile=" + profile + "]";
	}
}