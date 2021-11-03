package com.codegym.casemd6.service.song;

import com.codegym.casemd6.model.Song;
import com.codegym.casemd6.service.IGeneralService;

public interface ISongService extends IGeneralService<Song> {
    public Iterable<Song> findByAcount(Long id);
}
