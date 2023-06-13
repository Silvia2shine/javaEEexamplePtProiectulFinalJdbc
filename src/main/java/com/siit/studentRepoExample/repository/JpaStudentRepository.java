package com.siit.studentRepoExample.repository;

import com.siit.studentRepoExample.model.Student;
import jakarta.persistence.*;

import java.util.List;

public class JpaStudentRepository {
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
    EntityManager entityManager = emFactory.createEntityManager();

    public List<Student> getAllStudents() {

        TypedQuery<Student> typedQuery = entityManager.createQuery("select s from Student s", Student.class);
        List<Student> studentList = typedQuery.getResultList();
        entityManager.close();
        emFactory.close();
        return studentList;


//        List<Student> studentList = typedQuery.getResultList();
//        return studentList;

//        Query that does not check for type (UNSAFE)
//        Query query = entityManager.createQuery("select s from Student s");
    }

    /**
     * get all students in one line with unsafe casting, avoid!!
     */
    public List<Student> getAllStudentsOneLine() {
        return entityManager.createQuery("from Student").getResultList();
    }

    public void addStudent(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

//TODO  add method here to add a Student to the database

//TODO  add method here to find a Student in the database by name

//TODO  add method here to find a Student in the database by id

//TODO modify the JSP file to use the new methods instead of the JDBC one

}
