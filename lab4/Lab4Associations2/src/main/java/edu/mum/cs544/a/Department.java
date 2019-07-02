package edu.mum.cs544.a;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employeeList = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }


    public boolean addEmployee(Employee e) {
        boolean res = employeeList.add(e);
        if (res) {
            e.setDepartment(this);
        }
        return res;
    }

    public boolean removeEmployee(Employee e) {
        boolean isRemoved = employeeList.remove(e);
        if (isRemoved) {
            e.setDepartment(null);
        }
        return isRemoved;
    }


}
