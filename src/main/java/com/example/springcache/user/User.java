package com.example.springcache.user;

import javax.persistence.*;

@Entity
public class User
{
    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
