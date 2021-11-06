package com.codegym.casemd6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "playlist")
//@JsonIgnoreProperties(value = {"commentList", "likeList", "songs"}, allowGetters = true, ignoreUnknown = true)
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date date;
    private String image;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(
                    name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "song_id", referencedColumnName = "id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Song> songs;

}
