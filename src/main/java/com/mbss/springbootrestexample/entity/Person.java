package com.mbss.springbootrestexample.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="people")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @ManyToMany(targetEntity = Person.class, cascade = MERGE)
    private List<Person> friends;

    @ManyToMany(mappedBy = "friends")
    private List<Person> friendOf;
}
