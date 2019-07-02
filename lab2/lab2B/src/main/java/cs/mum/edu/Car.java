package cs.mum.edu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue
    private long id;
    private String brand;
    private String year;
    private double price;


    public Car(String brand, String year, double price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
    }
}
