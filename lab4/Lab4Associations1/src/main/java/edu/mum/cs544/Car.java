package edu.mum.cs544;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Car {
    @Id
    @GeneratedValue
    private long id;
    private String brand;
    private String year;
    private double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Owner owner;


    public Car(String brand, String year, double price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
    }


}
