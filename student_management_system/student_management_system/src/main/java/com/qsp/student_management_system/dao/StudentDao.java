package com.qsp.student_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.student_management_system.dto.Student;
import com.qsp.student_management_system.repo.StudentRepo;

@Repository
public class StudentDao {

	@Autowired
	private StudentRepo repo;

	public Student saveStudent(Student student) {
		return repo.save(student);
	}

	public List<Student> saveAllStudent(List<Student> students) {
		return repo.saveAll(students);
	}

	public Student login(String email, String password) {

		try {
			long phone = Long.parseLong(email);
			Student student = repo.findByPhone(phone);
			if (student != null && password.equals(student.getPassword())) {

				return student;
			} else {
				return null;
			}

		} catch (Exception e) {
			Student student = repo.findByEmail(email);
			if (student != null && password.equals(student.getPassword())) {

				return student;
			} else {
				return null;
			}
		}

	}

	public Student findById(int id) {
		Optional<Student> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Student findByPhone(long phone) {
		return repo.findByPhone(phone);
	}

	public Student findByEmail(String email) {
		return repo.findByEmail(email);
	}

	public List<Student> findByName(String name) {
		return repo.findByName(name);
	}

	public List<Student> findByFatherName(String fatherName) {
		return repo.findByFatherName(fatherName);
	}

	public List<Student> findByMotherName(String motherName) {
		return repo.findByMotherName(motherName);
	}

	public List<Student> findByAddress(String address) {
		return repo.findByAddress(address);
	}

	public List<Student> findByGrade(String grade) {
		return repo.findByGrade(grade);
	}

	public List<Student> findByPercentage(double percentage) {
		return repo.findByPercentage(percentage);
	}

	public List<Student> findByPercentageLessThan(double percentage) {
		return repo.findByPercentageLessThan(percentage);
	}

	public List<Student> findByPercentageGreaterThan(double percentage) {
		return repo.findByPercentageGreaterThan(percentage);
	}

	public List<Student> findByPercentageBetween(double percentage1, double percentage2) {
		return repo.findByPercentageBetween(percentage1, percentage2);
	}

	public List<Student> findAllStudents() {
		return repo.findAll();
	}

	public Student updateStudentName(int id, String name) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			Student student = optional.get();
			student.setName(name);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student updateStudentFatherName(int id, String fatherName) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			Student student = optional.get();
			student.setFatherName(fatherName);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student updateStudentMotherName(int id, String motherName) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			Student student = optional.get();
			student.setMotherName(motherName);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student updateStudentPhone(int id, long phone) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			Student student = optional.get();
			student.setPhone(phone);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student updateStudentAddress(int id, String address) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			Student student = optional.get();
			student.setAddress(address);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student updateStudentEmail(int id, String email) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			Student student = optional.get();
			student.setEmail(email);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student updateStudentPassword(int id, String password) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			Student student = optional.get();
			student.setPassword(password);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student updateStudentAllDetails(int id, Student student) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			student.setId(id);
			return repo.save(student);
		} else {
			return null;
		}
	}

	public Student deleteStudentById(int id) {
		Optional<Student> optional = repo.findById(id);

		if (optional.isPresent()) {
			repo.deleteById(id);
			return optional.get();
		} else {
			return null;
		}
	}

	public Student deleteStudentByPhone(long phone) {
		Student student = repo.findByPhone(phone);

		if (student != null) {
			repo.delete(student);
			return student;
		} else {
			return null;
		}
	}

	public Student deleteStudentByEmail(String email) {
		Student student = repo.findByEmail(email);
		if (student != null) {
			repo.delete(student);
			return student;
		} else {
			return null;
		}
	}

	public List<Student> deleteStudentByFatherName(String fatherName) {
		List<Student> list = repo.findByFatherName(fatherName);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Student> deleteStudentByMotherName(String motherName) {
		List<Student> list = repo.findByMotherName(motherName);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Student> deleteStudentByAddress(String address) {
		List<Student> list = repo.findByAddress(address);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Student> deleteStudentByPercentage(double percentage) {
		List<Student> list = repo.findByPercentage(percentage);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Student> deleteStudentByLessThanPercentage(double percentage) {
		List<Student> list = repo.findByPercentageLessThan(percentage);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Student> deleteStudentByGreaterThanPercentage(double percentage) {
		List<Student> list = repo.findByPercentageGreaterThan(percentage);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Student> deleteStudentByBetweenPercentage(double percentage1, double percentage2) {
		List<Student> list = repo.findByPercentageBetween(percentage1, percentage2);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}

	public List<Student> deleteStudentByGrade(String grade) {
		List<Student> list = repo.findByGrade(grade);

		if (list.isEmpty()) {
			return list;
		} else {
			repo.deleteAll(list);
			return list;
		}
	}
	
	public List<Student> deleteAllStudent() {
		List<Student>list=repo.findAll();
		repo.deleteAll();
		return list;
	}
}
