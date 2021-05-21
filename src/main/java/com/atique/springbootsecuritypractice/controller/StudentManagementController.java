package com.atique.springbootsecuritypractice.controller;

import com.atique.springbootsecuritypractice.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/management/api/student")
@Slf4j
public class StudentManagementController {

    private List<Student> STUDENTS = new ArrayList<>(Arrays.asList(
            Student.builder().id(1).name("A").build(),
            Student.builder().id(2).name("B").build(),
            Student.builder().id(3).name("C").build(),
            Student.builder().id(4).name("D").build()
    ));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Student> getAllStudent() {
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public Student registerStudent(@RequestBody Student request) {
        Optional<Student> existing = STUDENTS.stream()
                .filter(student -> request.getId().equals(student.getId()))
                .findFirst();

        if (!existing.isEmpty()) {
            throw new IllegalStateException(request.toString() + " already exist.");
        }
        STUDENTS.add(request);
        log.info("new student registered: {}", request);
        return request;
    }

    @PutMapping(value = "/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public Student updateStudent(@PathVariable(value = "studentId") Integer studentId, @RequestBody Student request) {
        STUDENTS.stream()
                .filter(student -> studentId.equals(student.getId()))
                .findFirst()
                .map(student -> {
                    student.setName(request.getName());
                    return student;
                })
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist"));
        log.info("Student updated with studentId: {}", studentId);
        return request;
    }

    @DeleteMapping(value = "/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    void deleteStudent(@PathVariable(value = "studentId") Integer studentId) {
        STUDENTS.stream()
                .filter(student -> studentId.equals(student.getId()))
                .findFirst()
                .map(student -> {
                    STUDENTS.remove(student);
                    return student;
                })
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist"));
    }

}
