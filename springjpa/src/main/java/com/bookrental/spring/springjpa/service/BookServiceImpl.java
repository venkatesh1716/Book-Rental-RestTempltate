package com.bookrental.spring.springjpa.service;


import com.bookrental.spring.springjpa.doa.BookRepo;

import com.bookrental.spring.springjpa.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {


    @Autowired
    BookRepo bookRepo;

    @Override
    public List<Book> getAllbooks() {
        return bookRepo.findAll();
    }
/*
    @Override
    public List<Book> getAllbooks() {
        return bookRepo.getAllbook();
    }*/


    @Override
    public Optional<Book> getBookById(int bookid) {
        return bookRepo.findById(bookid);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book updateBook(Book book1) {
        return bookRepo.save(book1);
    }

    @Override
    public void deleteBookid(int bookid) {
        bookRepo.deleteBookByBookID(bookid);
    }
    @Override
    public List<Book> deleteAllBook() {
        bookRepo.deleteAll();
        return null;
    }

}
