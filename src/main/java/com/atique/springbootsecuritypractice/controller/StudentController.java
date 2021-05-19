package com.atique.springbootsecuritypractice.controller;

import com.atique.springbootsecuritypractice.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            Student.builder().id(1).name("A").build(),
            Student.builder().id(2).name("B").build(),
            Student.builder().id(3).name("C").build(),
            Student.builder().id(4).name("D").build()
    );

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return STUDENTS.stream()
                .filter(student -> id.equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " does not exist"));
    }
}
