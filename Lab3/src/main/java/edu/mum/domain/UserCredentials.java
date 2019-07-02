package edu.mum.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "authentication")
@Entity
public class UserCredentials {

    @Id
    @Column(name = "user", length = 250)
    private String username;
    private String password;
    @Transient
    private String verifyPassword;
    private Boolean enabled;


}
