package edu.mum.cs544;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "passenger_id")
    @OrderColumn(name = "sequence")
    List<Flight> flightlist = new ArrayList<>();

    public Passenger(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlightlist(List<Flight> flightlist) {
        System.out.println("===============setFlightlist===============");
        this.flightlist = flightlist;
    }

    public List<Flight> getFlightlist() {
        System.out.println("===============getFlightlist===============");
//        return Collections.unmodifiableList(flightlist);
        return flightlist;
    }

    public boolean addFlight(Flight flight) {
        return flightlist.add(flight);
    }

    public boolean removeFlight(Flight flight) {
        return flightlist.remove(flight);
    }


}
