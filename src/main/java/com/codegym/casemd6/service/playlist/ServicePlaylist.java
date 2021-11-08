package com.codegym.casemd6.service.playlist;

import com.codegym.casemd6.model.Playlist;
import com.codegym.casemd6.repository.IPlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePlaylist implements IServicePlaylist {
    @Autowired
    IPlaylistRepo playlistRepo;

    @Override
    public Iterable<Playlist> findAll() {
        return playlistRepo.findAll();
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistRepo.findById(id);
    }

    @Override
    public void save(Playlist playlist) {
        playlistRepo.save(playlist);
    }

    @Override
    public void remove(Long id) {
        playlistRepo.deleteById(id);
    }
}
