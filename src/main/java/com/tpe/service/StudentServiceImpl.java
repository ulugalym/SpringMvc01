package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository repo;

    @Override
    public void saveStudent(Student student) {
        repo.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return repo.getAll();
    }

    @Override
    public Student findStudentById(Long id) {

        Student student=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found by id : "+id));
        //get gelen optional bossa NoSuchElementException


        return student;
    }

    @Override
    public void deleteStudent(Long id) {

        Student student=findStudentById(id);//silmeye calismadan bu idye sahip student yoksa exception firlatir
        repo.delete(student.getId());
    }
}
