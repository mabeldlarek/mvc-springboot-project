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
    public ClubDTO getById(Long id) {
        Club club = clubRepository.findById(id).get();
        return mapToDTO(club);
    }

    @Override
    public ClubDTO createClub(Club club) {
        clubRepository.save(club);
        return mapToDTO(club);
    }

    @Override
    public ClubDTO updateClub(Club club, Long id) {
        Club clubUpdate = clubRepository.findById(id).get();
        if(clubUpdate != null) {
            clubUpdate.setId(id);
            clubUpdate.setTitle(club.getTitle());
            clubUpdate.setContent(club.getContent());
            clubUpdate.setPhotoUrl(club.getPhotoUrl());
            clubUpdate.setUpdateOn(club.getUpdateOn());
            clubUpdate.setCreatedOn(club.getCreatedOn());
            clubRepository.save(clubUpdate);
        }

        return mapToDTO(clubUpdate);
    }

    @Override
    public String deleteClub(Long id) {
        clubRepository.deleteById(id);
        return "deleted successfully";
    }


    private Club mapToEntity(ClubDTO clubDTO){
        Club club = Club.builder().
                id(clubDTO.getId()).
                title(clubDTO.getTitle()).
                photoUrl(clubDTO.getPhotoUrl()).
                content(clubDTO.getContent())
                .createdOn(clubDTO.getCreatedOn())
                .updateOn(clubDTO.getUpdateOn())
                .build();
        return  club;
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
