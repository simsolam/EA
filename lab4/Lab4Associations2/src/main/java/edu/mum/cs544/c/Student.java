package edu.mum.cs544.c;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Student {

	@Id
//	@Column(length = 250)
	@Setter
	@Getter
	private Long studentid;

	@Setter
	@Getter
	private String firstname;

	@Setter
	@Getter
	private String lastname;

	@ManyToMany
	@JoinTable(name = "Student_Course")
	Collection<Course> courselist = new ArrayList<Course>();

	public Student(Long studentid, String firstname, String lastname) {
		this.studentid = studentid;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Collection<Course> getCourselist() {
		return courselist;
	}

	public void addCourse(Course course) {
		boolean added = courselist.add(course);
		if(added){
			course.getStudentlist().add(this);
		}
	}

	public String getName(){
		return firstname + " " + lastname;
	}

}
