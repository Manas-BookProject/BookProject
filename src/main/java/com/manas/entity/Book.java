package com.manas.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", allocationSize = 1, initialValue = 7)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private BigDecimal price;

    private String author;

    @Column(length = 10000)
    private String description;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH})
    private Vendor owner;

    private String image;
}