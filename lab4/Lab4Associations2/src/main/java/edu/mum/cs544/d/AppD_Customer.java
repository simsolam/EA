package edu.mum.cs544.d;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppD_Customer {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // setup data
        Customer c = new Customer("Jack");
        c.addReservation(new Reservation(LocalDate.now()));
        c.addReservation(new Reservation(LocalDate.now()));
        em.persist(c);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // get data
        @SuppressWarnings("unchecked")
        Collection<Customer> custs = em.createQuery("from Customer").getResultList();
        for (Customer cust : custs) {
            System.out.println(c.getName());
            for (Reservation r : cust.getReservations()) {
                System.out.println(r.getDate());
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
