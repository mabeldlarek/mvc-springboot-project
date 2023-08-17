package com.mabeldlarek.web.service;



import com.mabeldlarek.web.dto.ClubDto;
import com.mabeldlarek.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);
    void updateClub(ClubDto club);

    void delete(long clubId);

    List<ClubDto> searchClubs(String query);
}
