package com.manas.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", allocationSize = 1, initialValue = 7)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {PERSIST,MERGE,DETACH,REFRESH})
    @JoinColumn(name = "buyer_id")
    private User user;

    @ManyToOne(cascade = {PERSIST,MERGE,DETACH})
    private Vendor vendor;

    @OneToOne(cascade = {PERSIST,MERGE,DETACH,REFRESH})
    private Book book;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

}