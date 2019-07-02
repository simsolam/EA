package edu.mum.cs544.c;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String coursenumber;

    private String name;

    public Course(String coursenumber, String name) {
        this.coursenumber = coursenumber;
        this.name = name;
    }

    @ManyToMany(mappedBy = "courselist", cascade = CascadeType.PERSIST)
    private Collection<Student> studentlist = new ArrayList<>();

    public Collection<Student> getStudentlist() {
        return studentlist;
    }

    public void addStudent(Student student) {
        studentlist.add(student);
    }

}
