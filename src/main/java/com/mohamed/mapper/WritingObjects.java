package com.mohamed.mapper;

import com.mohamed.mapper.model.Product;
import com.mohamed.mapper.orm.EntityManager;

public class WritingObjects {

	public static void main(String[] args) throws Exception {
		
		EntityManager<Product> entityManager = EntityManager.of(Product.class);
		
		Product mobile = new Product("Mobile", 50);
		Product computer = new Product("Computer", 20);
		Product printer = new Product("Printer", 10);
		
		System.out.println(mobile);
		System.out.println(computer);
		System.out.println(printer);

		System.out.println("Writing to DB");
		
		entityManager.persist(mobile);
		entityManager.persist(computer);
		entityManager.persist(printer);
		
		System.out.println(mobile);
		System.out.println(computer);
		System.out.println(printer);
	}
}
