package com.codegym.casemd6.repository;

import com.codegym.casemd6.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaylistRepo extends JpaRepository<Playlist,Long> {
}
