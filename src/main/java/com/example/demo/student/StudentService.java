package com.example.demo.student;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}


	public List<Student> getStudents()
	{
		return studentRepository.findAll();
	}


	public void addStudent(Student student) {
		Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
		
		if(optionalStudent.isPresent())
		{
			throw new IllegalStateException("Email is taken");
		}
		studentRepository.save(student);
	}


	public void deleteStudent(Long studentId) {
		boolean exist = studentRepository.existsById(studentId);
		if(!exist)
		{
			throw new IllegalStateException("No Student with ID:"+ studentId + " exist");
		}
		
		studentRepository.deleteById(studentId);	
	}


	public Optional<Student> getStudent(Long studentId) {
		boolean exist = studentRepository.existsById(studentId);
		if(!exist)
		{
			throw new IllegalStateException("No Student with ID:"+ studentId + " exist");
		}
		return studentRepository.findById(studentId);
	}


	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(() ->
		new IllegalStateException("No Student with ID:"+ studentId + " exist"));
		
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name))
		{
			student.setName(name);
		}
		
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email))
		{
			Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
			
			if(optionalStudent.isPresent())
			{
				throw new IllegalStateException("Email is taken");
			}
			
			student.setEmail(email);
		}
	}


	
}
