package com.reactminitest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    public enum Roles{
        SELLER,BUYER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Roles name;
}
