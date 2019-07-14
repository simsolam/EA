package edu.mum.cs544;

import java.util.List;

public interface IBookDao {

	public abstract List<Book> getAll();

	public abstract void add(Book car);

	public abstract Book get(int id);

	public abstract void update(Book car);

	public abstract void delete(int carId);

}