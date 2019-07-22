package com.bookrental.spring.springjpa;

import com.bookrental.spring.springjpa.RestTemplateService.RestTemplateService;
import com.bookrental.spring.springjpa.entity.Book;

public class MainRunner {

    public static void main(String[] args) {
        RestTemplateService restTemplateService= new RestTemplateService();

        //to run all the methods individually

       /* Book book=new Book("java","spring",554555,"academic",46);
        restTemplateService.addBook(book);*/

        //restTemplateService.getAllBooks();

         //restTemplateService.getBooksById();

        // restTemplateService.updateBook();

         //  restTemplateService.deleteBook();

        //restTemplateService.getAllBorrowers();

        // restTemplateService.getBorrowerById();

        // restTemplateService.RentBook();

        //restTemplateService.deleteBorrower();

        //restTemplateService.updateBorrower();
    }
}
