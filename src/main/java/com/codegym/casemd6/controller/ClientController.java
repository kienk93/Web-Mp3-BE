package com.codegym.casemd6.controller;

import com.codegym.casemd6.model.Comment;
import com.codegym.casemd6.model.Playlist;
import com.codegym.casemd6.model.Song;
import com.codegym.casemd6.service.comment.IServiceComment;
import com.codegym.casemd6.service.playlist.IServicePlaylist;
import com.codegym.casemd6.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
@EnableSpringDataWebSupport
public class ClientController {
    @Autowired
    ISongService songService;
    @Autowired
    IServiceComment serviceComment;

    @Autowired
    IServicePlaylist servicePlaylist;

    @GetMapping("/latest")
    public ResponseEntity<Page<Song>> latest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Song> songPage = songService.findAllLestes(pageable);
        return new ResponseEntity<>(songPage, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Page<Song>> count() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("count").descending());
        Page<Song> songPage = songService.findAllLestes(pageable);
        return new ResponseEntity<>(songPage, HttpStatus.OK);
    }

    @GetMapping("/likes")
    public ResponseEntity<Page<Song>> likes() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("countLike").descending());
        Page<Song> songPage = songService.findAllLestes(pageable);
        return new ResponseEntity<>(songPage, HttpStatus.OK);
    }

    @PutMapping("/updateView")
    public ResponseEntity<String> updateSong(@RequestBody Long idSong) {
        Song song = songService.findById(idSong).get();
        Long count = song.getCount()+1;
        song.setCount(count);
        System.out.println("luot nghi sau khi tang" + song.getCount());
        songService.save(song);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping("/comment/{idSong}")
    public ResponseEntity<List<Comment>> fillAllComment(@PathVariable("idSong") Long idSong) {
        List<Comment> list = serviceComment.findAllComment(idSong);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> addPlaylist(){
//        Playlist playlist = new Playlist();
//        playlist.setName("playlist 1");
//        Song song1 = songService.findById(1L).get();
//        Song song2 = songService.findById(2L).get();
//        song1.getPlaylists().add(playlist);
//        song2.getPlaylists().add(playlist);
//        songService.save(song1);
//        songService.save(song2);

        Playlist playlist = servicePlaylist.findById(1L).get();
        Song song3 = songService.findById(3L).get();
        playlist.getSongs().add(song3);
        song3.getPlaylists().add(playlist);
        servicePlaylist.save(playlist);
        return new ResponseEntity<>("ok",HttpStatus.ACCEPTED);
    }
}
