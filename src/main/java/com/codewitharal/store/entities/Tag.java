package com.codewitharal.store.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "tags")
    private HashSet<User> users= new HashSet<>();


    public Tag(String name) {
        this.name = name;
    }
}
