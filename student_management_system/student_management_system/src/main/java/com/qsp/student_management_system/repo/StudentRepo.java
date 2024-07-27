package com.qsp.student_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.student_management_system.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student findByPhone(long phone);
	
	Student findByEmail(String email);
	
	List<Student> findByName(String name);
	
	List<Student> findByFatherName(String fatherName);
	
	List<Student> findByMotherName(String motherName);
	
	List<Student> findByAddress(String address);
	
	List<Student> findByPercentage(double percentage);
	
	List<Student> findByPercentageLessThan(double percentage);
	
	List<Student> findByPercentageGreaterThan(double percentage);
	
	List<Student> findByPercentageBetween(double percentage1 , double percentage2);
	
	List<Student> findByGrade(String grade);
	
	
}
