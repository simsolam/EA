package edu.mum.cs544.a;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
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

    public Employee(String name) {
        this.name = name;
    }
}
