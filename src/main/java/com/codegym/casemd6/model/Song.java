package com.codegym.casemd6.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "song")
public class Song implements Comparable<Song> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;
    private String singer;
    private Long count;
    private Long countLike;
    @ManyToOne
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "song", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<AccountLike> likeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "song", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<Comment> commentList;

    @Override
    public int compareTo(Song o) {
        return this.likeList.size() - o.likeList.size();
    }
}
