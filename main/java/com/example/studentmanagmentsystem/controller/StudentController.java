package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.model.Student;
import com.example.studentmanagmentsystem.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PostMapping("/multiple-students")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
        List<Student> savedStudents = studentService.addStudents(students);
        return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
    }

    @GetMapping("/student-surname")
    public ResponseEntity<List<Student>> searchStudentBySurname(@RequestParam String surname) {
        List<Student> students = studentService.searchStudentBySurname(surname);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> searchStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> searchAllStudents() {
        List<Student> students = studentService.searchAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
