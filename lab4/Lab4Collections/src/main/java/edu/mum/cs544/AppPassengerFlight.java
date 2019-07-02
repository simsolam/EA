package edu.mum.cs544;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Collection;

public class AppPassengerFlight {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Flight flight1 = new Flight(10034, "Amsterdam", "Boston", LocalDateTime.now());
        Flight flight2 = new Flight(10045, "Amsterdam", "Chicago", LocalDateTime.now());
        Flight flight3 = new Flight(10072, "Chicago", "Paris", LocalDateTime.now());
        Passenger passenger = new Passenger("Frank Brown");

        // please note, the order in which they are added here
        // should also be the order in which they are printed
        passenger.addFlight(flight1);
        passenger.addFlight(flight3);
        passenger.addFlight(flight2);

        em.persist(passenger);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        Collection<Passenger> passengerList = em.createQuery("from Passenger").getResultList();
        for (Passenger p : passengerList) {
            System.out.println("passenger name = " + p.getName());
            for (Flight flight : p.getFlightlist()) {
                System.out.println("flight nr= " + flight.getFlightnumber()
                        + " from= " + flight.getFrom() + " to= " + flight.getTo());
            }
        }
        em.getTransaction().commit();
        em.close();

    }
}

