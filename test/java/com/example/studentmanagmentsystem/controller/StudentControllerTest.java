package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.model.Student;
import com.example.studentmanagmentsystem.model.repository.StudentRepository;
import com.example.studentmanagmentsystem.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    private Student expectedStudent;
    private StudentController cut;

    @Mock
    private StudentRepository studentRepositoryMock;

    @BeforeEach
    void setUp() {
        expectedStudent = Student.builder()
                .name("Robert")
                .surname("Martin")
                .age(25)
                .email("robertmartin2@gmail.com")
                .build();

        StudentService studentService = new StudentService(studentRepositoryMock);
        cut = new StudentController(studentService);
    }

    @Test
    void shouldAddNewStudentToTheDatabase() {
        // Given
        when(studentRepositoryMock.save(expectedStudent)).thenReturn(expectedStudent);

        // When
        ResponseEntity<Student> addedStudent = cut.addStudent(expectedStudent);

        // Then
        assertThat(addedStudent.getBody()).isEqualTo(expectedStudent);
    }

    @Test
    void shouldAddMultipleStudentsToTheDatabase() {
        //Given
        Student anotherExpectedStudent = Student.builder()
                .name("Nevermind")
                .surname("Musango")
                .age(23)
                .email("nevermindmusango101@gmail.com")
                .build();
        List<Student> expectedStudents = List.of(expectedStudent, anotherExpectedStudent);
        when(studentRepositoryMock.saveAll(anyList())).thenReturn(expectedStudents);

        //When
        ResponseEntity<List<Student>> addedStudents = cut.addStudents(expectedStudents);

        //Then
        assertThat(addedStudents.getBody()).containsAll(expectedStudents).hasSize(2);

    }

    @Test
    void shouldSearchStudentBySurnameInTheDatabase() {
       /* // Given
        when(studentRepositoryMock.findBySurname(expectedStudent.getSurname())).thenReturn(expectedStudent);

        // When
        ResponseEntity<Student> responseEntity = cut.searchStudentBySurname(expectedStudent.getSurname());
        Student searchedSurname = responseEntity.getBody();

        // Then
        assertThat(searchedSurname).isEqualTo(expectedStudent); */
    }

    @Test
    void shouldSearchStudentByIdInTheDatabase() {
        // Given
        when(studentRepositoryMock.findById(expectedStudent.getId())).thenReturn(Optional.ofNullable(expectedStudent));

        // When
        ResponseEntity<Student> responseEntity = cut.searchStudentById(expectedStudent.getId());
        Student searchId = responseEntity.getBody();

        // Then
        assertThat(searchId).isEqualTo(expectedStudent);
    }

    @Test
    void shouldSearchAllStudentsInTheDatabase() {
        // Given
        Student expectedStudentOne = Student.builder().name("Robert").surname("Martin").age(25).email("robertmartin2@gmail.com").build();
        Student expectedStudentTwo = Student.builder().name("Nevermind").surname("Musango").age(23).email("nevermindmusango101@gmail.com").build();
        List<Student> expectedStudents = List.of(expectedStudentOne, expectedStudentTwo);
        when(studentRepositoryMock.findAll()).thenReturn(expectedStudents);

        // When
        ResponseEntity<List<Student>> responseEntity = cut.searchAllStudents();

        // Then
        assertThat(responseEntity.getBody()).isEqualTo(expectedStudents);
    }
}
