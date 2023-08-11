package com.mabeldlarek.web.service;

import com.mabeldlarek.web.dtos.ClubDTO;
import com.mabeldlarek.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAll();
    ClubDTO createClub(Club club);

    ClubDTO findClubById(Long clubId);
    void updateClub(ClubDTO club);
    String deleteClub(Long id);

}
