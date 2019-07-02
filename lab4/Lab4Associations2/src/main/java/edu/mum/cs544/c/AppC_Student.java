package edu.mum.cs544.c;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppC_Student {

	private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student student1 = new Student(1L, "Frank","Brown");
        Student student2 = new Student(2L, "John","Doe");
        Course course1 = new Course("CS544", "EA");
        Course course2 = new Course("CS545", "WAA");
        course1.addStudent(student1);
//        student1.addCourse(course1);
        course1.addStudent(student2);
//        student2.addCourse(course1);
        course2.addStudent(student1);
//        student1.addCourse(course2);

        em.persist(course1);
        em.persist(course2);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        Collection<Student> studentList = em.createQuery("from Student").getResultList();
        for (Student student : studentList) {
            System.out.println("student name = " + student.getName());
            for (Course course : student.getCourselist()) {
                System.out.println("course name= " + course.getName());
            }
        }
        System.out.println("---- now the reverse -----");
        @SuppressWarnings("unchecked")
        Collection<Course> courseList = em.createQuery("from Course").getResultList();
        for (Course course : courseList) {
            System.out.println("course name= " + course.getName());
            for (Student student : course.getStudentlist()) {
                System.out.println("student name = " + student.getName());
            }
        }
        em.getTransaction().commit();
        em.close();
    }
}
