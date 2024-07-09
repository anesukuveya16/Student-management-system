package com.example.studentmanagmentsystem.model.repository;

import com.example.studentmanagmentsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> getStudentById(Long id);

    List<Student> findBySurname(String surname);
}
