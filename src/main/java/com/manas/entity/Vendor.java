package com.manas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@Table(name = "vendor")
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_gen")
    @SequenceGenerator(name = "vendor_gen", sequenceName = "vendor_seq")
    private Long id;
    private String firstName;
    private String lastName;

    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "created_at")
    private LocalDate createdAt;

    private String image;
    private String description;

    @OneToMany( cascade = ALL)
    private List<Transaction> transactions;

    @OneToMany(cascade = ALL)
    private List<Book> books;

}