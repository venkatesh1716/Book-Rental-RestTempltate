package entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@Component
@Entity
@Table(name = "employeess")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String firstname;
    private String lastname;

    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z-]+" +
            "(\\.A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @Email
    private String email;

    private String loan;
    private String bid;
    private String status;

    public Borrower() {

    }

    public Borrower(String firstname, String lastname, String email, String loan, String bid,String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.loan = loan;
        this.bid = bid;
        this.status=status;
    }
    public Borrower(String firstname, String lastname, String email, String loan, String bid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.loan = loan;
        this.bid = bid;

    }

}
