package com.mabeldlarek.web.service;

import com.mabeldlarek.web.dtos.ClubDTO;
import com.mabeldlarek.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAll();
    ClubDTO getById(Long id);
    ClubDTO createClub(Club club);
    ClubDTO updateClub(Club club, Long id);
    String deleteClub(Long id);

}
