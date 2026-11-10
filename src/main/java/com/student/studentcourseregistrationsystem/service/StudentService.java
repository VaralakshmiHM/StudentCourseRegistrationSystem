package com.student.studentcourseregistrationsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentcourseregistrationsystem.model.Student;
import com.student.studentcourseregistrationsystem.exception.StudentNotFoundException;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student(1, "Ananya", "ananya@gmail.com", "Java"));
        students.add(new Student(2, "Rahul", "rahul@gmail.com", "Python"));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public Student getStudentById(int id) {
        return students.stream()
                .filter(student -> student.getStudentId() == id)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    public Student updateStudent(int id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);

        existingStudent.setStudentName(updatedStudent.getStudentName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCourseName(updatedStudent.getCourseName());

        return existingStudent;
    }

    public String deleteStudent(int id) {
        Student student = getStudentById(id);
        students.remove(student);
        return "Student deleted successfully";
    }
}