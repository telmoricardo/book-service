package br.com.telmo.bookservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK", schema = "BOOK_SERVICE")
public class Book implements Serializable {

    private static final long serialVersionUID = 2847026927573075543L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    @SequenceGenerator(name = "BOOK_SEQ", sequenceName = "BOOK_SEQ", schema = "BOOK_SERVICE", allocationSize = 1)
    private Long id;

    @Column(name= "author", nullable = false,  length = 180)
    private String author;

    @Column(name= "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 250)
    private String title;

    @Transient
    private String currency;

    @Transient
    private String enviroment;
}
