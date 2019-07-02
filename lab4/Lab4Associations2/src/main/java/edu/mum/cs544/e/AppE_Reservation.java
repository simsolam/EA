package edu.mum.cs544.e;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppE_Reservation {

	private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // setup data
        Customer c = new Customer("Jack");
        c.addReservation(new Reservation(LocalDate.now(), new Book("123432123", "Java Patterns", "Sam Cooke")));
        c.addReservation(new Reservation(LocalDate.now(), new Book("543234564", "Design Patterns", "Erich Gamma")));
        em.persist(c);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // get data
        @SuppressWarnings("unchecked")
        Collection<Customer> custs = em.createQuery("from ECustomer").getResultList();
        for (Customer cust : custs) {
            System.out.println(c.getName());
            for (Reservation r : cust.getReservations()) {
                System.out.println(r.getDate());
                System.out.println(r.getBook());
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
