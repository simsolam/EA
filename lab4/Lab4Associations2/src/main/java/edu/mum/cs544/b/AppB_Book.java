package edu.mum.cs544.b;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppB_Book {

	private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = new Book("123432123", "Java Patterns", "Sam Cooke");
        Publisher publisher = new Publisher("Quick books");
        book.setPublisher(publisher);
        em.persist(book);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        Collection<Book> bookList = em.createQuery("from Book").getResultList();
        for (Book b : bookList) {
            System.out.println("Book title = " + b.getTitle());
            System.out.println("Publisher  = "
                    + b.getPublisher().getName());

        }
        em.getTransaction().commit();
        em.close();
    }
}
