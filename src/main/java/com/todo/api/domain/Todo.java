package com.todo.api.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Todo {
    @Id @GeneratedValue
    private Long id;

    @Lob
    @Column(nullable = false)
    private String todo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
}
