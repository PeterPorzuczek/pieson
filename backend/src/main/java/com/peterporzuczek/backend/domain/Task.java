package com.peterporzuczek.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

// @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "id")
@Table(name = "user_task")
public class Task {
    @Id
    @JsonIdentityReference(alwaysAsId=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identification;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="username")
    private String username;

    @Column(name = "time")
    private String time;
}
