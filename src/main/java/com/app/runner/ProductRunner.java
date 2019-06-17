package com.app.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.app.model.Product;
import com.app.repo.ProductRepository;

@Component
public class ProductRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;

	@Override
	public void run(String... args) throws Exception {

		/** 1. findAll( ) : List<T> -> [select * form <table-name>] **/
		// This method is used to fetch the all records returns List collection.	

		List<Product> list =repo.findAll();
		list.forEach(System.out::println);


		/** 2. findAll(Sort):void -> [select * from prodtab order by pcode asc] **/
		// Here "sort" is class used to provide "order by clause" for select query

		System.out.println("-----Sorting Output----");
		repo.findAll(Sort.by("prodCode").descending())
		.forEach(System.out::println);


		/** 3. findAll(Pageable) **/
		//This method is used to fetch the data based on pagination
		// Pageable is interface and its impl class is "PageRequest"

		System.out.println("-----Pageable Output----");
		PageRequest p=PageRequest.of(2, 3);
		repo.findAll(p).forEach(System.out::println);

		/** 4. findAll(Example) **/
		// Example-It is a interface which takes a sample class object need to be 
		// compared with every row only not null values
		// supports sort,pageable.
		// matching rows are selected as output
		System.out.println("----Example -------");
		// a]....
		Product prd =new Product();
		prd.setProdCost(2.2);
		Example<Product> ex=Example.of(prd);
		repo.findAll(ex).forEach(System.out::println);

		System.out.println("----Example With Sorting -------");
		// b]...Example With Sorting.
		Product prd1 =new Product();
		prd1.setProdCost(2.2);
		Example<Product> ex1 =Example.of(prd1);
		repo.findAll(ex1,Sort.by(Direction.DESC, "prodCode")).forEach(System.out::println);;

		System.out.println("----Example With Paging -------");
		// c]...Exmple with Paging.
		Product prd2 =new Product();
		prd2.setProdCost(2.2);
		Example<Product> ex2 =Example.of(prd2);
		repo.findAll(ex2, PageRequest.of(0, 2)).forEach(System.out::println);;

		System.out.println("----ByID -------");
		/** 5. findAllById(..) :void **/
		// Fetch records which are selected on primary key.
		
		repo.findAllById(Arrays.asList(1,6,8,10,12)).forEach(System.out::println);
		
	}

}
