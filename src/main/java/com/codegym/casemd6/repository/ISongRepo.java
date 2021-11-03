package com.codegym.casemd6.repository;

import com.codegym.casemd6.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepo extends JpaRepository<Song,Long> {
}
