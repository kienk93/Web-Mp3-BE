package com.codegym.casemd6.controller;

import com.codegym.casemd6.dto.CommentDto;
import com.codegym.casemd6.dto.MesageRespons;
import com.codegym.casemd6.dto.SongInit;
import com.codegym.casemd6.model.Account;
import com.codegym.casemd6.model.Comment;
import com.codegym.casemd6.model.Song;
import com.codegym.casemd6.service.account.IServiceAccount;
import com.codegym.casemd6.service.comment.IServiceComment;
import com.codegym.casemd6.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
@EnableSpringDataWebSupport
public class MusicController {
    @Autowired
    ISongService songService;
    @Autowired
    IServiceAccount serviceAccount;

    @Autowired
    IServiceComment serviceComment;

    @PostMapping("/create")
    public ResponseEntity<MesageRespons> createSong(@RequestBody SongInit song) {
        Account account = serviceAccount.findById(song.getIdAccount()).get();
        Song newSong = new Song();
        newSong.setName(song.getName());
        newSong.setPath(song.getPath());
        newSong.setSinger(song.getSinger());
        newSong.setAccount(account);
        newSong.setCount(0L);
        newSong.setCountLike(0L);
        songService.save(newSong);
        MesageRespons mesageRespons = new MesageRespons();
        mesageRespons.setMesage("ok");
        return new ResponseEntity<>(mesageRespons, HttpStatus.OK);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<List<Song>> findAllSongByAccount(@PathVariable("id") Long idAccount) {
        List<Song> songList = new ArrayList<>();
        songList = (List<Song>) songService.findByAcount(idAccount);
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<MesageRespons> createSong(@RequestBody CommentDto comment) {
        Comment newComment = new Comment();
        Account account = serviceAccount.findById(comment.getIdAccount()).get();
        Song song = songService.findById(comment.getIdSong()).get();
        String text = comment.getText();
        newComment.setAccount(account);
        newComment.setSong(song);
        newComment.setText(text);
        serviceComment.save(newComment);
        MesageRespons mesageRespons = new MesageRespons();
        mesageRespons.setMesage("ok");
        return new ResponseEntity<>(mesageRespons, HttpStatus.OK);
    }

}
