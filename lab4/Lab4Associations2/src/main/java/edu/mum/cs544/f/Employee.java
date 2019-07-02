package edu.mum.cs544.f;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity(name="FEmployee")
public class Employee {

    @Id
    @Column(length = 250)
    private String employeenumber;

    private String name;

    public Employee(String employeenumber, String name) {
        this.employeenumber = employeenumber;
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.PERSIST) //MUST have Cascade here in order to save Office automatically
    @JoinColumn(name = "office_id")
    private Office office;

    public Employee(String name) {
        this.name = name;
    }
}
