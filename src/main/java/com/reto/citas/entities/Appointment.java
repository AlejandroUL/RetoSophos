package com.reto.citas.entities;

import java.time.LocalDate;
import java.time.LocalTime;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appointments")
public class Appointment {

	public Appointment() {
		super();
	}

	public Appointment(Long appointmentId, LocalDate dateAppointment, LocalTime hourAppointment, Affiliates affiliateId,
			Tests testId) {
		super();
		this.appointmentId = appointmentId;
		this.dateAppointment = dateAppointment;
		this.hourAppointment = hourAppointment;
		this.affiliateId = affiliateId;
		this.testId = testId;
	}


	@Id
	@Column(name="appointmentId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;
	
	@Column(name="appointmentdate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate dateAppointment;
	
	@Column(name="appointmenthour")
	@JsonFormat(pattern = "HH:mm")
	@DateTimeFormat(pattern = "HH:mm" )
	private LocalTime hourAppointment;
	

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name="affiliate_Id")
	private Affiliates affiliateId;
	
	@ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name="test_Id")
	private Tests testId;

	public Long getAppointmentId() {
		return appointmentId;
	}



	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}



	public LocalDate getDateAppointment() {
		return dateAppointment;
	}



	public void setDateAppointment(LocalDate dateAppointment) {
		this.dateAppointment = dateAppointment;
	}



	public LocalTime getHourAppointment() {
		return hourAppointment;
	}



	public void setHourAppointment(LocalTime hourAppointment) {
		this.hourAppointment = hourAppointment;
	}



	public Affiliates getAffiliateId() {
		return affiliateId;
	}



	public void setAffiliateId(Affiliates affiliateId) {
		this.affiliateId = affiliateId;
	}



	public Tests getTestId() {
		return testId;
	}



	public void setTestId(Tests testId) {
		this.testId = testId;
	}

}

