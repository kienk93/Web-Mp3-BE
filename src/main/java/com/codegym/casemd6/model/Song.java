package com.codegym.casemd6.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//@Table(name = "song")
//@JsonIgnoreProperties(value = {"commentList", "likeList", "playlists"}, allowGetters = true, ignoreUnknown = true)
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

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AccountLike.class, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<AccountLike> likeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<Comment> commentList;

    @Override
    public int compareTo(Song o) {
        return this.likeList.size() - o.likeList.size();
    }

    @JsonBackReference(value = "playlist-song")
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Playlist.class)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Playlist> playlists;
}
