package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping("/books") // @RequestMapping(value = "/books",method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> list = this.service.getBooks();

		if (list.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.of(Optional.of(list));
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		Book book = null;
		book = this.service.getBook(id);

		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else
			return ResponseEntity.of(Optional.of(book));

	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBookHandler(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.service.addNewBook(book);
			System.out.println(b);

			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBookHandler(@PathVariable("id") int id, @RequestBody Book book) {
		Book b = null;

		try {
			b = this.service.updateBook(id, book);
			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBookHandler(@PathVariable("id") int id) {
		try {
			this.service.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
}
