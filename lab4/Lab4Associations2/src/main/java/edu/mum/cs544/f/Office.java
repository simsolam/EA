package edu.mum.cs544.f;

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
public class Office {

    @Id
    @Column(length = 250)
    private String roomnumber;

    private String building;

    @OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST)
    private List<Employee> employeeList = new ArrayList<>();

    public Office(String roomnumber, String building) {
        this.roomnumber = roomnumber;
        this.building = building;
    }
}
