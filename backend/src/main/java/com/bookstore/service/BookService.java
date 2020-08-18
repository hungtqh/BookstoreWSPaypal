package com.bookstore.service;

import java.util.List;

import com.bookstore.domain.Book;

public interface BookService {
	
	List<Book> findAll();
	
	List<Book> findAllForAdmin();
	
	Book findOne(Long id);
	
	Book save(Book book);
	
	List<Book> blurrySearch(String title);
	
	void removeOne(Long id);

	List<Book> findNewBook();
}
