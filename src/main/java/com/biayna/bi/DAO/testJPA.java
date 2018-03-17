package com.biayna.bi.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class testJPA {
	
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	
	public static void main (String[] args){
		
		begin();
		
		//persistBook (new Book(null, "book title", "fred", 9.99f));
		//updateBook (new Book(1L, "book title", "hello", 9.99f));
		//findBook (1L);
		//query();
		remove();
		end();
		
	}
	
	
	private static void begin() {
		factory = Persistence.createEntityManagerFactory("biayna_bi");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();		
	}
	
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.clear();
		entityManager.close();
	}

	private static void persistBook(Book book) {
		entityManager.persist(book);
	}
	private static void updateBook(Book book) {
		entityManager.merge(book);
	}
	private static void findBook(Long id) {
		Book book = entityManager.find(Book.class, id);
		System.out.println("\n Find Book: " + book.toString());
	}
	private static void query() {
		String jpql = "Select b From Book b Where b.price < 30";
		Query query = entityManager.createQuery(jpql);
		List<Book> listBooks = query.getResultList();
		for (Book b : listBooks) {
			System.out.println();
			System.out.println(b.getTitle());
			System.out.println(b.getAuthor());
			System.out.println(b.getPrice());
		}
	}
	private static void remove() {
		Long primaryKey = 2L;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}
}
