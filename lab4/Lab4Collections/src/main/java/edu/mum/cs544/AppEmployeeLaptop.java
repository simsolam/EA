package edu.mum.cs544;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AppEmployeeLaptop {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Employee emp1 = new Employee("Frank", "Brown");
        Laptop laptop1 = new Laptop("Acer", "Aspire");
        Laptop laptop2 = new Laptop("DELL", "Studio");
        emp1.addLaptop(laptop1); // automatically sets bi-directional association
        emp1.addLaptop(laptop2);

        em.persist(emp1);

        Employee emp2 = new Employee("Frank2", "Brown2");
        Laptop laptop3 = new Laptop("Acer2", "Aspire2");
        Laptop laptop4 = new Laptop("DELL2", "Studio2");
        emp2.addLaptop(laptop3); // automatically sets bi-directional association
        emp2.addLaptop(laptop4);
        em.persist(emp2);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        Collection<Employee> emps = em.createQuery("from Employee").getResultList();
        for (Employee emp : emps) {
            System.out.println("Employe name = " + emp.getFirstname() + " " + emp.getLastname());
            for (Laptop laptop : emp.getLaptoplist()) { //N+1 problem
                System.out.println("laptop brand= " + laptop.getBrand() + " type= " + laptop.getType());
            }
        }

        em.getTransaction().commit();
        em.close();

    }
}

