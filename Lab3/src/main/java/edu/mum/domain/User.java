package edu.mum.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Table(name="users")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int rating = 0;

    @Column(name = "is_admin")
    private boolean admin = false;

    @Version
    private int version = 0;

    private Date lastLogin;

    public User(String firstName, String lastName, String email, int rating, boolean admin, int version, Date lastLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rating = rating;
        this.admin = admin;
        this.version = version;
        this.lastLogin = lastLogin;
    }


}