package com.manas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter @Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToOne(cascade = ALL)
    private Account account;

    @OneToMany(cascade = ALL)
    private List<Transaction> transactions;

    @OneToOne(cascade = ALL, mappedBy = "user")
    private Application application;
}