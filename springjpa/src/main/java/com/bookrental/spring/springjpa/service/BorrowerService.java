package com.bookrental.spring.springjpa.service;


import com.bookrental.spring.springjpa.entity.Borrower;

import java.util.List;
import java.util.Optional;

public interface BorrowerService {

    List<Borrower> getBorrowers();

    Optional<Borrower> getBorrowerById(int id);

    Borrower rentBook(Borrower borrower);

    Borrower updateBorrower(Borrower borrower);

    void deleteBorrowerById(int id);

    void deleteAllBorrowers();


}