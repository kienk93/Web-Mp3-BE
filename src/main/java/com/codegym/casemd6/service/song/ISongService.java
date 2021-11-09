package com.codegym.casemd6.service.song;

import com.codegym.casemd6.model.Song;
import com.codegym.casemd6.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISongService extends IGeneralService<Song> {
    Iterable<Song> findByAcount(Long id);

    Page<Song> findAllLestes(Pageable pageable);
    Page<Song> findAllCount(Pageable pageable);
    Page<Song> findSongsByNameContaining(Pageable pageable, String name);
}
