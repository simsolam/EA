package edu.mum.cs544.e;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "EReservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Book book;

    public Reservation(LocalDate date) {
        this.date = date;
    }

    public Reservation(LocalDate date, Book book) {
        this.date = date;
        this.book = book;
    }
}
