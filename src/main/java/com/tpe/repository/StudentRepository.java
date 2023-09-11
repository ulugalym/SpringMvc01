package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void save(Student student);
    List<Student>getAll();
    Optional<Student> findById(Long id);//null pointer exception almamak icin
                                        //null yerine bos bir optional objesi doner
    void delete(Long id);

}
