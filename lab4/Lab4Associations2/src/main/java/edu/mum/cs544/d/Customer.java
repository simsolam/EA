package edu.mum.cs544.d;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST) //MUST have cascade here, otherwise cannot save Reservation
    private Collection<Reservation> reservations = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public boolean addReservation(Reservation reservation){
        return reservations.add(reservation);
    }

}
