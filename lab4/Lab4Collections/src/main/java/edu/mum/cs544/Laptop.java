package edu.mum.cs544;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String type;

    @ManyToOne
    private Employee employee;

    public Laptop(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    /**
     * cannot use id as the here.
     * Id is generated by the DB. the time add into Set, doesn't have Id value
     * will cause NullPointerException
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(brand, laptop.brand) &&
                Objects.equals(type, laptop.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, type);
    }
}
