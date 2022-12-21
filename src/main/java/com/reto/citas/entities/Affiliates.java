package com.reto.citas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="affiliates")
public class Affiliates {


	public Affiliates() {
		super();
	}



	public Affiliates(Long affiliateId, String affiliatesName, int affiliatesAge, String mail) {
		super();
		this.affiliateId = affiliateId;
		this.affiliatesName = affiliatesName;
		this.affiliatesAge = affiliatesAge;
		this.mail = mail;
	}



	@Id
	@Column(name="affiliate_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long affiliateId;
	
	@Column(name="affiliatesName")
	private String affiliatesName;
	
	@Column(name="affiliatesAge")
	private int affiliatesAge;
	
	@Column(name="mail")
	private String mail;

	public Long getAffiliateId() {
		return affiliateId;
	}



	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}



	public String getAffiliatesName() {
		return affiliatesName;
	}



	public void setAffiliatesName(String affiliatesName) {
		this.affiliatesName = affiliatesName;
	}



	public int getAffiliatesAge() {
		return affiliatesAge;
	}



	public void setAffiliatesAge(int affiliatesAge) {
		this.affiliatesAge = affiliatesAge;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Affiliates(Long affiliateId) {
		super();
		this.affiliateId = affiliateId;
	}
}
