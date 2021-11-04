package com.codegym.casemd6.controller;

import com.codegym.casemd6.model.Song;
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


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
@EnableSpringDataWebSupport
public class ClientController {
    @Autowired
    ISongService songService;

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

    @PutMapping("/upadateSong")
    public ResponseEntity<String> updateSong(@RequestBody Song song) {
        songService.save(song);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
