package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Role(String type) {
        this.type = type;
    }
}
