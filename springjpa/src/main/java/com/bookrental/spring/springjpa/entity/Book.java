package com.bookrental.spring.springjpa.entity;


import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Data
@Component
@Entity
@Table(name = "book")
public class Book {

    @Id
    private int bookID;

    private String authorname;
    private String title;
    private String isbn;
    private String academic;
    private String totalcount;

    private String status;


    public Book() {
    }

    public Book( String authorname, String title, String isbn, String academic, String totalcount,int bookID) {
        this.bookID = bookID;
        this.authorname = authorname;
        this.title = title;
        this.isbn = isbn;
        this.academic = academic;
        this.totalcount = totalcount;

    }

}
