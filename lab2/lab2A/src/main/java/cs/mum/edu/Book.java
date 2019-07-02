package cs.mum.edu;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String ISBN;
    private String author;
    private double price;
    @Temporal(TemporalType.DATE)
    private java.util.Date publish_date;

    public Book(String title, String ISBN, String author, double price, Date publish_date) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.publish_date = publish_date;
    }

}
