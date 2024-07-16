package com.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

@Component//@Service
public class BookService {
	
	//private static List<Book> list = new ArrayList<>();
//	
//	static
//	{
//		list.add(new Book(101,"Complete Reference-Java","XYZ"));
//		list.add(new Book(102,"Think Java","PQR"));
//		list.add(new Book(103,"Let us C ","ABC"));
//	}
	
	@Autowired
	BookRepository bookRepo;
	
	// return list of all books
	
	public List<Book> getBooks()
	{
		List<Book> list =  bookRepo.findAll();
		return list;
	}

	// return a single book 
	
	public Book getBook(Integer id) {
		
		// search the book from list using it's id and return it.
		Book book = null;
		
		try{
			
			book = bookRepo.findById(id).get();
		}
		catch (Exception e) {}
		
		return book;
	}

	// insert a new book
	
	public Book addNewBook(Book book) {
		
		Book b = bookRepo.save(book);
		System.out.println("Book is successfully added ");
		System.out.println(b);
		
		return b;
	}

	// modify existing book
	
	public Book updateBook(int id, Book book) {
		
		Book b = null;
		b = bookRepo.findById(id).get();
		book.setBookId(id);
		b = bookRepo.save(book);
		
		return b;
	}

	// remove a book 
	
	public void deleteBook(int id) {
		
		Book b = null;
		b=bookRepo.findById(id).get();
		System.out.println(b);
		bookRepo.deleteById(id);
		
	}
	
}
