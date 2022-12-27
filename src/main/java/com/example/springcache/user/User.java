package com.example.springcache.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable
{
    private String name;
    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
