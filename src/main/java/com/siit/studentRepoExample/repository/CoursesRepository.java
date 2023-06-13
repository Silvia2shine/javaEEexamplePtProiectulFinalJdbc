package com.siit.studentRepoExample.repository;

import com.siit.studentRepoExample.model.Course;
import com.siit.studentRepoExample.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CoursesRepository {
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
    EntityManager entityManager = emFactory.createEntityManager();

    public List<Course> getAllCourses() {
        TypedQuery<Course> typedQuery = entityManager.createQuery("select c from Course c", Course.class);
        List<Course> courses = typedQuery.getResultList();
        entityManager.close();
        emFactory.close();
        return courses;
        //TODO add data to the link table, finish the addCourse method, create an api to add courses, create jsp to add courses
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



}
