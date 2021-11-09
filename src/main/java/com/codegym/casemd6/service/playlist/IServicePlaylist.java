package com.codegym.casemd6.service.playlist;

import com.codegym.casemd6.model.Playlist;
import com.codegym.casemd6.service.IGeneralService;

import java.util.List;

public interface IServicePlaylist extends IGeneralService<Playlist> {
    List<Playlist> findAllByAccount_Id(Long idAccount);
}
