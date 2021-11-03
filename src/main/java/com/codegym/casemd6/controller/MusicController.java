package com.codegym.casemd6.controller;

import com.codegym.casemd6.dto.MesageRespons;
import com.codegym.casemd6.model.Account;
import com.codegym.casemd6.model.Song;
import com.codegym.casemd6.service.account.IServiceAccount;
import com.codegym.casemd6.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
@EnableSpringDataWebSupport
public class MusicController {
    @Autowired
    ISongService songService;
    @Autowired
    IServiceAccount serviceAccount;

    @PostMapping("/create")
    public ResponseEntity<MesageRespons> createSong(@RequestBody Song song) {
        Account account = serviceAccount.findById(song.getAccount().getId()).get();
        song.setAccount(account);
        songService.save(song);
        MesageRespons mesageRespons = new MesageRespons();
        mesageRespons.setMesage("ok");
        return new ResponseEntity<>(mesageRespons, HttpStatus.OK);
    }
}
