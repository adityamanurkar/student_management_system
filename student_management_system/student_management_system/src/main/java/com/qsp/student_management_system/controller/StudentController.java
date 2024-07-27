package com.qsp.student_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.student_management_system.dto.Student;
import com.qsp.student_management_system.service.StudentService;
import com.qsp.student_management_system.util.ResponseStructure;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}
	
	@PostMapping("/save/all")
	public ResponseEntity<ResponseStructure<List<Student>>> saveAllStudent(@RequestBody List<Student> student) {
		return service.saveAllStudent(student);
	}
	
	@GetMapping("/find/byid")
	public ResponseEntity<ResponseStructure<Student>> findById(@RequestParam int id) {
		return service.findById(id);
	}

	@GetMapping("/find/byphone")
	public ResponseEntity<ResponseStructure<Student>> findByPhone(@RequestParam long phone) {
		return service.findByPhone(phone);
	}
	
	@GetMapping("/find/byemail")
	public ResponseEntity<ResponseStructure<Student>> findByEmail(@RequestParam String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping("/find/byname")
	public ResponseEntity<ResponseStructure<List<Student>>> findByName(@RequestParam String name) {
		return service.findByName(name);
	}
	
	@GetMapping("/find/byfathername")
	public ResponseEntity<ResponseStructure<List<Student>>> findByFatherName(@RequestParam String fatherName) {
		return service.findByFatherName(fatherName);
	}
	
	@GetMapping("/find/bymothername")
	public ResponseEntity<ResponseStructure<List<Student>>> findByMotherName(@RequestParam String motherName) {
		return service.findByMotherName(motherName);
	}
	
	@GetMapping("/find/byaddress")
	public ResponseEntity<ResponseStructure<List<Student>>> findByAddress(@RequestParam String address) {
		return service.findByAddress(address);
	}
	
	@GetMapping("/find/bygrade")
	public ResponseEntity<ResponseStructure<List<Student>>> findByGrade(@RequestParam String grade) {
		return service.findByGrade(grade);
	}
	
	@GetMapping("/find/bypercentage")
	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentage(@RequestParam double percentage) {
		return service.findByPercentage(percentage);
	}
	
	@GetMapping("/find/bypercentage/less")
	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageLessThan(@RequestParam double percentage) {
		return service.findByPercentageLessThan(percentage);
	}
	
	@GetMapping("/find/bypercentage/greater")
	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageGreaterThan(@RequestParam double percentage) {
		return service.findByPercentageGreaterThan(percentage);
	}
	
	@GetMapping("/find/bypercentage/between")
	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageBetween(@RequestParam double percentage1, @RequestParam double percentage2) {
		return service.findByPercentageBetween(percentage1, percentage2);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<ResponseStructure<List<Student>>> findAllStudents() {
		return service.findAllStudents();
	}
	
	@GetMapping("/login")
	public Student login(@RequestParam String email ,@RequestParam String password) {
		return service.login(email, password);
	}
	
	@PatchMapping("/update/student/name")
	public ResponseEntity<ResponseStructure<Student>> updateStudentName(@RequestParam int id ,@RequestParam String name) {
		return service.updateStudentName(id, name);
	}
	
	@PatchMapping("/update/student/fathername")
	public ResponseEntity<ResponseStructure<Student>> updateStudentFatherName(@RequestParam int id ,@RequestParam String fatherName) {
		return service.updateStudentFatherName(id, fatherName);
	}
	
	@PatchMapping("/update/student/mothername")
	public ResponseEntity<ResponseStructure<Student>> updateStudentMotherName(@RequestParam int id ,@RequestParam String motherName) {
		return service.updateStudentMotherName(id, motherName);
	}
	
	@PatchMapping("/update/student/phone")
	public ResponseEntity<ResponseStructure<Student>> updateStudentPhone(@RequestParam int id ,@RequestParam long phone) {
		return service.updateStudentPhone(id, phone);
	}
	
	@PatchMapping("/update/student/address")
	public ResponseEntity<ResponseStructure<Student>> updateStudentAddress(@RequestParam int id ,@RequestParam String address) {
		return service.updateStudentAddress(id, address);
	}
	
	@PatchMapping("/update/student/email")
	public ResponseEntity<ResponseStructure<Student>> updateStudentEmail(@RequestParam int id ,@RequestParam String email) {
		return service.updateStudentEmail(id, email);
	}
	
	@PatchMapping("/update/student/password")
	public ResponseEntity<ResponseStructure<Student>> updateStudentPassword(@RequestParam int id ,@RequestParam String password) {
		return service.updateStudentPassword(id, password);
	}
	
	@PatchMapping("/update/student/marks")
	public ResponseEntity<ResponseStructure<Student>> updateStudentMarks(@RequestParam String email, @RequestParam String password, @RequestParam int securedMarks1,@RequestParam int totalMarks1) {
		return service.updateStudentMarks(email, password, securedMarks1, totalMarks1);

	}
	
	@PutMapping("/update/student/all/details")
	public ResponseEntity<ResponseStructure<Student>> updateStudentAllDetails(@RequestParam int id ,@RequestBody Student student) {
		return service.updateStudentAllDetails(id, student);
	}
	
	@DeleteMapping("/delete/student/byid")
	public ResponseEntity<ResponseStructure<Student>> deleteStudentById(@RequestParam int id) {
		return service.deleteStudentById(id);
	}
	
	@DeleteMapping("/delete/student/byphone")
	public ResponseEntity<ResponseStructure<Student>> deleteStudentByPhone(@RequestParam long phone) {
		return service.deleteStudentByPhone(phone);
	}
	
	@DeleteMapping("/delete/student/byemail")
	public ResponseEntity<ResponseStructure<Student>> deleteStudentByEmail(@RequestParam String email) {
		return service.deleteStudentByEmail(email);
	}
	
	@DeleteMapping("delete/student/byfathername")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByFatherName(@RequestParam String fatherName) {
		return service.deleteStudentByFatherName(fatherName);
	}
	
	@DeleteMapping("delete/student/bymothername")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByMotherName(@RequestParam String motherName) {
		return service.deleteStudentByMotherName(motherName);
	}
	
	@DeleteMapping("delete/student/byaddress")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByAddress(@RequestParam String address) {
		return service.deleteStudentByAddress(address);
	}
	
	@DeleteMapping("delete/student/bypercentage")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByPercentage(@RequestParam double percentage) {
		return service.deleteStudentByPercentage(percentage);
	}
	
	@DeleteMapping("delete/student/bylessthan/percentage")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByLessThanPercentage(@RequestParam double percentage) {
		return service.deleteStudentByLessThanPercentage(percentage);
	}
	
	@DeleteMapping("delete/student/bygreaterthan/percentage")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByGreaterThanPercentage(@RequestParam double percentage) {
		return service.deleteStudentByGreaterThanPercentage(percentage);
	}
	
	@DeleteMapping("delete/student/bybetween/percentage")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByBetweenPercentage(@RequestParam double percentage1,@RequestParam double percentage2) {
		return service.deleteStudentByBetweenPercentage(percentage1, percentage2);
	}
	
	@DeleteMapping("delete/student/bygrade")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByGrade(@RequestParam String grade) {
		return service.deleteStudentByGrade(grade);
	}
	
	@DeleteMapping("delete/all/student")
	public ResponseEntity<ResponseStructure<List<Student>>> deleteAllStudent() {
		return service.deleteAllStudent();
	}
}
