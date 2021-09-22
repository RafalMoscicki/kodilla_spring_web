package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "description")
    private String content;

    @Column(name = "name")
    private String title;
}
