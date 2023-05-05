package com.reactminitest.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"username"})
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;
}
