package edu.mum.cs544;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookDao implements IBookDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Book> getAll() {
		return em.createQuery("from Book", Book.class).getResultList();
	}
	
	@Override
	public void add(Book book) {
		em.persist(book);
	}
	
	@Override
	public Book get(int id) {
		return em.find(Book.class, id);
	}
	
	@Override
	public void update(Book book) {
		em.merge(book);
	}
	
	@Override
	public void delete(int id) {
		Book c = em.getReference(Book.class, id);
		em.remove(c);
	}
}
