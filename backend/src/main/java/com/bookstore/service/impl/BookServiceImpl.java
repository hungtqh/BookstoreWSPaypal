package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		
		List<Book> activeBookList = new ArrayList<>();
		
		for (Book book : bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}
	
	@Override
	public List<Book> findAllForAdmin() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		
		return bookList;
	}
	
	public Book findOne(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> blurrySearch(String keyword) {
		List<Book> bookList = bookRepository.findByTitleContaining(keyword);
		
		List<Book> activeBookList = new ArrayList<>();
		
		for (Book book : bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}
	
	public void removeOne(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> findNewBook() {
		return bookRepository.findTop6ByActiveTrueOrderByPublicationDateDesc();
	}
}
