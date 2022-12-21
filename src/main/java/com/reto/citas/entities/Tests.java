package com.reto.citas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tests")
public class Tests {

	
	
	
	public Tests() {
		super();
	}

	

	public Tests(Long testId, String name, String description) {
		super();
		this.testId = testId;
		this.name = name;
		this.description = description;
	}

	@Id
	@Column(name="test_Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Tests(Long testId) {
		super();
		this.testId = testId;
	}
}
