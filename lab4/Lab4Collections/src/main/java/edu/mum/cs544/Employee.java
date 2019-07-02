package edu.mum.cs544;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<Laptop> laptops = new HashSet<>();

    public Employee(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Set<Laptop> getLaptoplist() {
        return Collections.unmodifiableSet(laptops);
    }

    public boolean addLaptop(Laptop laptop) {
        boolean added = laptops.add(laptop);
        if (added) {
            laptop.setEmployee(this);
        }
        return added;
    }

    public boolean removeLaptop(Laptop laptop) {
        boolean removed = laptops.remove(laptop);
        if (removed) {
            laptop.setEmployee(null);
        }
        return removed;
    }

    /**
     * Are these methods actually override Hibernate's implementation?
     * It seems never been called. It should use reflection when @Id is added on Field
     */
//    public Set<Laptop> getLaptops() {
//        System.out.println("==================getLaptops================");
//        return laptops;
//    }
//
//    public void setLaptops(Set<Laptop> laptops) {
//        System.out.println("================setLaptops==================");
//        this.laptops = laptops;
//    }
}
