package com.bookrental.spring.springjpa.service;

import com.bookrental.spring.springjpa.doa.BorrowerRepo;
import com.bookrental.spring.springjpa.entity.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    BorrowerRepo dao;

    @Override
    public List<Borrower> getBorrowers() {
        return dao.findAll();
    }

    @Override
    public Optional<Borrower> getBorrowerById(int id) {
        return dao.findById(id);
    }

    @Override
    public Borrower rentBook(Borrower borrower) {
        return dao.save(borrower);
    }


    @Override
    public Borrower updateBorrower(Borrower borrower) {
        return dao.save(borrower);
    }

    @Override
    public void deleteBorrowerById(int id) {
        dao.deleteBookByBookID(id);
    }

    @Override
    public void deleteAllBorrowers() {
        dao.deleteAll();
    }
}