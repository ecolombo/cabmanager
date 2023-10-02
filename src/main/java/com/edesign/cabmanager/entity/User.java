package com.edesign.cabmanager.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@SequenceGenerator(name="user_seq", sequenceName = "user_seq", initialValue =1, allocationSize = 1 )
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name="user_id")
	private int userId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="phone")
	private String phone;

	@Column(name="login_type_identifier", columnDefinition = "integer default 1") // 0=user, 1=admin
	private int loginType;
	
	@Column(name="auth_token")
	private String authToken = UUID.randomUUID().toString();
	
	@Column(name="added_on")
	private Date addedOn = new Date();
	

}
