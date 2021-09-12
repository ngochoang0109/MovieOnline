package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "enable")
    private boolean enable;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<UserMovie> userMovies;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, boolean enable, Set<Role> roles, Set<UserMovie> userMovies) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.roles = roles;
        this.userMovies = userMovies;
    }

    public User() {
        this.enable=true;
    }
}
