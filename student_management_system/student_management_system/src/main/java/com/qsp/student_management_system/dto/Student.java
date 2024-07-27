package com.qsp.student_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student_info")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int id;
	
	@Column(name = "student_name")
	private String name;
	
	@Column(name = "student_fatherName")
	private String fatherName;
	
	@Column(name = "student_motherName")
	private String motherName;
	
	@Column(name = "student_phone" , unique = true)
	private long phone;
	
	@Column(name = "student_address")
	private String address;
	
	@Column(name = "student_email" , unique = true)
	private String email;
	
	@Column(name = "student_password")
	private String password;
	
	@Column(name = "student_securedMarks")
	private int securedMarks;
	
	@Column(name = "student_totalMarks")
	private int totalMarks;
	
	@Column(name = "student_percentage")
	private double percentage;
	
	@Column(name = "student_grade")
	private String grade;
	
}
