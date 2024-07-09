package com.example.studentmanagmentsystem.service;

import com.example.studentmanagmentsystem.model.Student;
import com.example.studentmanagmentsystem.model.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> addStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    public List<Student> searchStudentBySurname(String surname) {
        return studentRepository.findBySurname(surname);

    }
    public List<Student> searchAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}


