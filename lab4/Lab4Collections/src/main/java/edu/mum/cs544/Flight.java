package edu.mum.cs544;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int flightnumber;

    @Column(name="[from]")
    private String from;

    @Column(name="[to]")
    private String to;

    private LocalDateTime date;

    public Flight(int flightnumber, String from, String to, LocalDateTime date) {
        this.flightnumber = flightnumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }
}
