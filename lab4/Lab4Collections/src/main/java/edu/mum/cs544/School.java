package edu.mum.cs544;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "school")
    @MapKey(name = "studentid")
    private Map<Long, Student> studentMap = new HashMap<>();

    public School() {
    }

    public School(String name) {
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

    public Map<Long, Student> getStudentlist() {
        return studentMap;
    }

    public void setStudentMap(Map<Long, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public Student addStudent(Student student) {
        return studentMap.put(student.getStudentid(), student);
    }

    public Student removeStudent(Long id) {
        return studentMap.remove(id);
    }
}
