package com.codegym.casemd6.service.song;

import com.codegym.casemd6.model.Song;
import com.codegym.casemd6.repository.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceSong implements ISongService{
    @Autowired
    private ISongRepo songRepo;
    @Override
    public Iterable<Song> findAll() {
        return songRepo.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepo.findById(id);
    }

    @Override
    public void save(Song song) {
        songRepo.save(song);
    }

    @Override
    public void remove(Long id) {
        songRepo.deleteById(id);
    }

    public Iterable<Song> findByAcount(Long id){
        return songRepo.findAllByAccount_Id(id);
    }
}
