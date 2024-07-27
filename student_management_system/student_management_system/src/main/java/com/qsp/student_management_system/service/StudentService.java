package com.qsp.student_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.student_management_system.dao.StudentDao;
import com.qsp.student_management_system.dto.Student;
import com.qsp.student_management_system.util.ResponseStructure;

@Service
public class StudentService {

	@Autowired
	private StudentDao dao;

	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student) {

		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		int securedMarks = student.getSecuredMarks();
		int totalMarks = student.getTotalMarks();

		if (securedMarks <= totalMarks && securedMarks > 0 && totalMarks > 0) {
			double percentage = (((double) securedMarks) / ((double) totalMarks)) * 100;
			student.setPercentage(percentage);

			if (percentage >= 85) {
				student.setGrade("Distinction");
			} else if (percentage >= 70 && percentage < 85) {
				student.setGrade("First class");
			} else if (percentage >= 49 && percentage < 70) {
				student.setGrade("second class");
			} else if (percentage >= 35 && percentage < 35) {
				student.setGrade("Pass");
			} else {
				student.setGrade("Fail");
			}
			Student student1 = dao.saveStudent(student);

			if (student1 != null) {
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Saved Successfully");
				structure.setData(dao.saveStudent(student1));
				return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.CREATED); 
			}
		} else {
			structure.setStatus(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Wrong Input");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.CREATED);
		}
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("");
		structure.setData(null);
		return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<ResponseStructure<List<Student>>> saveAllStudent(List<Student> students) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		for (Student student : students) {

			int securedMarks = student.getSecuredMarks();
			int totalMarks = student.getTotalMarks();

			if (securedMarks <= totalMarks && securedMarks > 0 && totalMarks > 0) {
				double percentage = (((double) securedMarks) / ((double) totalMarks)) * 100;
				student.setPercentage(percentage);

				if (percentage >= 85) {
					student.setGrade("Distinction");
				} else if (percentage >= 70 && percentage < 85) {
					student.setGrade("First class");
				} else if (percentage >= 49 && percentage < 70) {
					student.setGrade("second class");
				} else if (percentage >= 35 && percentage < 35) {
					student.setGrade("Pass");
				} else {
					student.setGrade("Fail");
				}
			} else {
				structure.setStatus(HttpStatus.BAD_REQUEST.value());
				structure.setMessage("Wrong Input");
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.BAD_REQUEST);
			}
		}
		List<Student> list = dao.saveAllStudent(students);

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Empty List Found");
			structure.setData(students);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Saved Successfully");
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}


	}

	public ResponseEntity<ResponseStructure<Student>> findById(int id) {

		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		Student student = dao.findById(id);

		if (student != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(student);

			return new  ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> findByPhone(long phone) {
		Student student = dao.findByPhone(phone);

		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Student>> findByEmail(String email) {
		Student student = dao.findByEmail(email);

		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.FOUND);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByName(String name) {
		List<Student> list = dao.findByName(name);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByFatherName(String fatherName) {
		List<Student> list = dao.findByFatherName(fatherName);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByMotherName(String motherName) {
		List<Student> list = dao.findByMotherName(motherName);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByAddress(String address) {
		List<Student> list = dao.findByAddress(address);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByGrade(String grade) {
		List<Student> list = dao.findByGrade(grade);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentage(double percentage) {
		List<Student> list = dao.findByPercentage(percentage);
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageLessThan(double percentage) {
		List<Student> list = dao.findByPercentageLessThan(percentage);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageGreaterThan(double percentage) {
		List<Student> list = dao.findByPercentageGreaterThan(percentage);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageBetween(double percentage1, double percentage2) {
		List<Student> list = dao.findByPercentageBetween(percentage1, percentage2);

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findAllStudents() {
		List<Student> list = dao.findAllStudents();

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);

		} else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Found Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentName(int id, String name) {
		Student student = dao.updateStudentName(id, name);

		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentFatherName(int id, String fatherName) {
		Student student=dao.updateStudentFatherName(id, fatherName);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentMotherName(int id, String motherName) {
		Student student=dao.updateStudentMotherName(id, motherName);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentPhone(int id, long phone) {
		Student student=dao.updateStudentPhone(id, phone);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentAddress(int id, String address) {
		Student student=dao.updateStudentAddress(id, address);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentEmail(int id, String email) {
		Student student=dao.updateStudentEmail(id, email);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentPassword(int id, String password) {
		Student student=dao.updateStudentPassword(id, password);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentMarks(String email, String password, int securedMarks1 , int totalMarks1 ) {
		Student student = dao.login(email, password);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			int securedMarks = securedMarks1;
			int totalMarks = totalMarks1;
			if (totalMarks >= securedMarks && securedMarks > 0) {

				double percentage = (((double) securedMarks / ((double) totalMarks)) * 100);
				student.setSecuredMarks(securedMarks1);
				student.setTotalMarks(totalMarks1);
				student.setPercentage(percentage);
				if (percentage >= 85) {
					student.setGrade("Distinction");
				} else if (percentage >= 70 && percentage < 85) {
					student.setGrade("firstClass");
				} else if (percentage >= 49 && percentage < 70) {
					student.setGrade("secondClass");
				} else if (percentage >= 35 && percentage < 49) {
					student.setGrade("Pass");
				} else {
					student.setGrade("fail");
				}
			} else {
				structure.setStatus(HttpStatus.BAD_REQUEST.value());
				structure.setMessage("Wrong Marks Input");
				structure.setData(null);
				return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.BAD_REQUEST);
			}

		} else {
			structure.setStatus(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Wrong Login Input");
			structure.setData(null);
			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.BAD_REQUEST);
		}
		Student student2=dao.saveStudent(student);
		
		if (student2 != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudentAllDetails(int id, Student student) {
		Student student2=dao.updateStudentAllDetails(id, student);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student2 != null) {
			student2.setName(student.getName());
			student2.setFatherName(student.getFatherName());
			student2.setMotherName(student.getMotherName());
			student2.setPhone(student.getPhone());
			student2.setAddress(student.getAddress());
			student2.setEmail(student.getEmail());
			student2.setPassword(student.getPassword());
			student2.setSecuredMarks(student.getSecuredMarks());
			student2.setTotalMarks(student.getTotalMarks());
			
			int securedMarks = student.getSecuredMarks();
			int totalMarks = student.getTotalMarks();
			if (totalMarks >= securedMarks && securedMarks > 0) {

				double percentage = (((double) securedMarks / ((double) totalMarks)) * 100);
				student2.setSecuredMarks(student.getSecuredMarks());
				student2.setTotalMarks(student.getTotalMarks());
				student2.setPercentage(percentage);
				
				if (percentage >= 85) {
					student2.setGrade("Distinction");
				} else if (percentage >= 70 && percentage < 85) {
					student2.setGrade("firstClass");
				} else if (percentage >= 49 && percentage < 70) {
					student2.setGrade("secondClass");
				} else if (percentage >= 35 && percentage < 49) {
					student2.setGrade("Pass");
				} else {
					student2.setGrade("fail");
				}
			} else {
				structure.setStatus(HttpStatus.BAD_REQUEST.value());
				structure.setMessage("Wrong Marks Input");
				structure.setData(null);
				return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.BAD_REQUEST);
			}
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Data Updated Successfully");
			structure.setData(student2);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student2);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public Student login(String email, String password) {
		return dao.login(email, password);
	}

	public ResponseEntity<ResponseStructure<Student>> deleteStudentById(int id) {
		Student student=dao.deleteStudentById(id);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);
			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> deleteStudentByPhone(long phone) {
		Student student=dao.deleteStudentByPhone(phone);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> deleteStudentByEmail(String email) {
		Student student=dao.deleteStudentByEmail(email);
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();

		if (student != null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		} else {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(student);

			return  new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByFatherName(String fatherName) {
		List<Student> list=dao.deleteStudentByFatherName(fatherName);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByMotherName(String motherName) {
		List<Student>list=dao.deleteStudentByMotherName(motherName);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByAddress(String address) {
		List<Student>list=dao.deleteStudentByAddress(address);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByPercentage(double percentage) {
		List<Student>list=dao.deleteStudentByPercentage(percentage);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByLessThanPercentage(double percentage) {
		List<Student>list=dao.deleteStudentByLessThanPercentage(percentage);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByGreaterThanPercentage(double percentage) {
		List<Student>list=dao.deleteStudentByGreaterThanPercentage(percentage);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByBetweenPercentage(double percentage1, double percentage2) {
		List<Student>list=dao.deleteStudentByBetweenPercentage(percentage1, percentage2);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> deleteStudentByGrade(String grade) {
		List<Student>list=dao.deleteStudentByGrade(grade);
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> deleteAllStudent() {
		List<Student>list=dao.deleteAllStudent();
		
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();

		if (list.isEmpty()) {
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Not Found");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
		} else {
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Delete Successfully");
			structure.setData(list);

			return  new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
		}
	}
}
