package com.mabeldlarek.web.service;

import com.mabeldlarek.web.dtos.ClubDTO;
import com.mabeldlarek.web.models.Club;
import com.mabeldlarek.web.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService{

    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDTO> findAll() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToDTO(club)).collect(Collectors.toList());
    }

    @Override
    public ClubDTO createClub(Club club) {
        clubRepository.save(club);
        return mapToDTO(club);
    }

    @Override
    public ClubDTO findClubById(Long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToDTO(club);
    }

    @Override
    public void updateClub(ClubDTO clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    private Club mapToClub(ClubDTO club) {
        Club clubDto = Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updateOn(club.getUpdateOn())
                .build();
        return  clubDto;
    }


    @Override
    public String deleteClub(Long id) {
        clubRepository.deleteById(id);
        return "deleted successfully";
    }
    
    private ClubDTO mapToDTO(Club club){
        ClubDTO clubDTO = ClubDTO.builder().
                id(club.getId()).
                title(club.getTitle()).
                photoUrl(club.getPhotoUrl()).
                content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updateOn(club.getUpdateOn())
                .build();
        return  clubDTO;
    }


}
