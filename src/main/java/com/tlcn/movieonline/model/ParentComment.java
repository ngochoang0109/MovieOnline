package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "parentcomment")
@Getter
@Setter
@NoArgsConstructor
public class ParentComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Long value;

    public ParentComment(Long value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> comments;

}
