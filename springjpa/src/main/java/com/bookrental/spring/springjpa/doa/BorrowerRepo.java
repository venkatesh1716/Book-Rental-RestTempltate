package com.bookrental.spring.springjpa.doa;


import com.bookrental.spring.springjpa.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BorrowerRepo extends JpaRepository<Borrower, Integer> {


    /*@Modifying
    @Transactional
    @Query(value = "UPDATE book SET count_after_rent=totalcount-1 WHERE bookID=:id",nativeQuery = true)
    void updateRent(String id );*/


    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET status='deleted' WHERE id=:id",nativeQuery = true)
    void  deleteBookByBookID(int id);

}
