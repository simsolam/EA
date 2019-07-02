package edu.mum.cs544;

import java.util.Collection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppSchoolStudent {
    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student student1 = new Student(1L, "Frank", "Brown");
        Student student2 = new Student(2L, "John", "Doe");
        School school = new School("Happy HighSchool");
        school.addStudent(student1);
        school.addStudent(student2);

        em.persist(school);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        Collection<School> schoolList = em.createQuery("from School").getResultList();
        for (School s : schoolList) {
            System.out.println("school name = " + s.getName());
            for (Map.Entry<Long, Student> enty : s.getStudentlist().entrySet()) {
                System.out.println(" key = " + enty.getKey() + " studentname = "
                        + ((Student) enty.getValue()).getFirstname());
            }

        }
        em.getTransaction().commit();
        em.close();
    }
}
