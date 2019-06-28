package cs.mum.edu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static EntityManagerFactory emf;
    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("book");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Create new instance of Book and set values in it
        Book book1 = new Book("HarryPotter and The Sorcerer Stone", "123456789", "J.K Rowling",120.0,new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-15"));
        // save the book
        em.persist(book1);
        // Create new instance of book and set values in it
        Book book2 = new Book("Fantastic Beasts and Where To Find Them", "123456789", "J.K Rowling",120.0,new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-15"));
        // save the book
        em.persist(book2);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retrieve all books
        TypedQuery<Book> query = em.createQuery("from Book", Book.class);
        List<Book> bookList = query.getResultList();
        for (Book book : bookList) {
            System.out.println("name= " + book.getTitle() + ", year= "
                    + book.getPublish_date() + ", author= " + book.getAuthor());
        }
        em.getTransaction().commit();
        em.close();
    }
}
