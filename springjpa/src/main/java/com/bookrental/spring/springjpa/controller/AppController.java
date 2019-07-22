package com.bookrental.spring.springjpa.controller;

import com.bookrental.spring.springjpa.Exceptions.InsertIdExc;
import com.bookrental.spring.springjpa.Exceptions.InternalServerExc;
import com.bookrental.spring.springjpa.Exceptions.UserNotFoundException;
import com.bookrental.spring.springjpa.entity.Book;
import com.bookrental.spring.springjpa.entity.Borrower;
import com.bookrental.spring.springjpa.service.BookService;
import com.bookrental.spring.springjpa.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
public class AppController {

    @Autowired
    BorrowerService service;

    @Autowired
    BookService service1;

    @RequestMapping(value = "borrowers", method = RequestMethod.GET)
    public List<Borrower> getBorrowers() {
        System.out.println(this.getClass().getSimpleName() + " - Get all borrower details are excuted.");
        return service.getBorrowers();
    }

    @RequestMapping(value = "/borrowers/{id}", method = RequestMethod.GET)
    public Borrower getBorrowerById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Get borrower details by id is excuted.");

        Optional<Borrower> borrower = service.getBorrowerById(id);
        if (!borrower.isPresent())
            throw new UserNotFoundException("Could not find borrower with id- " + id);

        return borrower.get();
    }

    @RequestMapping(value = "/borrowers", method = RequestMethod.POST)
    public ResponseEntity<Void> rentBook(@Valid @RequestBody Borrower borrower,String id) {
        System.out.println(this.getClass().getSimpleName() + " - Rented Book successfully.");

        if (borrower.getFirstname() == null || borrower.getFirstname().isEmpty())
            throw  new InsertIdExc("Enter the First name");

        if (borrower.getLastname() == null || borrower.getLastname().isEmpty())
        throw  new InsertIdExc("Enter the Last name");

        if (borrower.getEmail() == null || borrower.getEmail().isEmpty())
            throw  new InsertIdExc("Enter the Email id");

        if (borrower.getLoan() == null)
            throw  new InsertIdExc("Enter the No of days Loan");
        borrower.setStatus("available");
            service.rentBook(borrower);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/borrowers/{id}", method = RequestMethod.PUT)
    public Borrower updateBorrower(@RequestBody Borrower borrower, @PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Update Borrower details by id" + id + " is excuted.");

        Optional<Borrower> borrower1 = service.getBorrowerById(id);
        if (!borrower1.isPresent())
            throw new UserNotFoundException("Could not find borrower with id- " + id);

        if (borrower.getFirstname() == null || borrower.getFirstname().isEmpty())
            borrower.setFirstname(borrower1.get().getFirstname());

        if (borrower.getLastname() == null || borrower.getLastname().isEmpty())
            borrower.setLastname(borrower1.get().getLastname());

        if (borrower.getEmail() == null || borrower.getEmail().isEmpty())
            borrower.setEmail(borrower1.get().getEmail());

        if (borrower.getLoan() == null)
            borrower.setLoan(borrower1.get().getLoan());
        //borrower.setId(id);
        return service.updateBorrower(borrower);
    }

    @RequestMapping(value = "/borrowers/{id}", method = RequestMethod.DELETE)
    public void deleteBorrwerById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Borrower with id"+id+"deleted");

        Optional<Borrower> borrower = service.getBorrowerById(id);
        if (!borrower.isPresent())
            throw new UserNotFoundException("Could not find Borrower with id- " + id);

        service.deleteBorrowerById(id);
    }

    @RequestMapping(value = "/borrowers/all", method = RequestMethod.DELETE)
    public void deleteAll() {
        System.out.println(this.getClass().getSimpleName() + " - All Borrower details are deleted");
        service.deleteAllBorrowers();
    }

    @RequestMapping(value = "books", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        System.out.println(this.getClass().getSimpleName() + " - Get all Books details are excuted.");
        return service1.getAllbooks();
    }
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable String id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Get Book details by id is executed.");

        Book book= service1.getBookById(Integer.parseInt(id)).orElse(null);
        if (book==null) {
            throw new UserNotFoundException("Could not find book with id- " + id);
        }
        return book;
    }


    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<Void> addbook(@Valid @RequestBody Book book) {

        if (book.getAuthorname() == null || book.getAcademic().isEmpty())
            throw  new InsertIdExc("Enter the Author Name");

        if (book.getTitle() == null || book.getTitle().isEmpty())
            throw  new InsertIdExc("Enter the Book name");

        if (book.getIsbn() == null)
            throw  new InsertIdExc("Enter ISBN");

        if (book.getAcademic() == null || book.getAcademic().isEmpty())
            throw  new InsertIdExc("Enter Book type(Academic/Non-Academic");

        if (book.getTotalcount() == null)
            throw  new InsertIdExc("Enter the No of days Loan");

        book.setStatus("available");
                service1.addBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book, @PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Update Book details by id" + id + " is excuted.");

        Optional<Book> book1 = service1.getBookById(id);
        if (!book1.isPresent())
            throw new UserNotFoundException("Could not find book with id- " + id);

        /* if the specified fields are empty then it sets the previous values,
        and the values which are mentioned will only modified
         */
        if (book.getBookID() == 0)
            book.setBookID(book1.get().getBookID());

        if (book.getAuthorname() == null || book.getAuthorname().isEmpty())
            book.setAuthorname(book1.get().getAuthorname());

        if (book.getTitle() == null || book.getTitle().isEmpty())
            book.setTitle(book1.get().getTitle());

        if (book.getIsbn()==null)
            book.setIsbn((book1.get().getIsbn()));

        if (book.getAcademic() == null|| book.getAcademic().isEmpty())
            book.setAcademic(book1.get().getAcademic());

        if (book.getTotalcount()==null)
            book.setTotalcount((book1.get().getTotalcount()));

        if (book.getStatus()==null || book.getStatus().isEmpty())
            book.setStatus((book1.get().getStatus()));
        return service1.updateBook(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public List<Book> deleteBookById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Book with id"+id+"deleted");

        Optional<Book> book = service1.getBookById(id);
        if (!book.isPresent())
            throw new UserNotFoundException("Could not find Book with id- " + id);
         service1.deleteBookid(id)  ;
         return service1.getAllbooks();
    }

    @RequestMapping(value = "/books", method = RequestMethod.DELETE)
    public void deleteAllBooks() {
        System.out.println(this.getClass().getSimpleName() + " - All Book details are deleted");
        service1.deleteAllBook();
    }


}
