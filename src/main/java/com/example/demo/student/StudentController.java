package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
	
	private final StudentService studentService;
	
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}


	@GetMapping
	public List<Student> getStudents()
	{
		return studentService.getStudents();
	}
	
	@GetMapping(path = "{studentId}")
	public Optional<Student> getStudent(@PathVariable("studentId") Long studentId)
	{
		return studentService.getStudent(studentId);
	}
	
	@PostMapping
	public void addStudent(@RequestBody Student student)
	{
		studentService.addStudent(student);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId")Long studentId, 
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email)
	{
		studentService.updateStudent(studentId,name,email);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId)
	{
		studentService.deleteStudent(studentId);
	}
}
