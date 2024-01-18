package com.huntthecode.springboottransactionmanagementrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
/*
Entity class for Store

Class used to create table structure by using the annotations in database.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="stores",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

    @OneToMany(mappedBy = "stores",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Transaction> transactions =new HashSet<>();
}
