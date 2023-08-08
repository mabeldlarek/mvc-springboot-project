package com.mabeldlarek.web.controller;


import com.mabeldlarek.web.dtos.ClubDTO;
import com.mabeldlarek.web.models.Club;
import com.mabeldlarek.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/")
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public ResponseEntity<List<ClubDTO>> getAllClubs(){
        return new ResponseEntity<>(clubService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ClubDTO> createClub(@RequestBody Club club){
        return new ResponseEntity<>(clubService.createClub(club), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ClubDTO> updateClub(@RequestBody Club club, @PathVariable Long id){
        return new ResponseEntity<>(clubService.updateClub(club, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteClub(@PathVariable Long id){
        return new ResponseEntity<>(clubService.deleteClub(id), HttpStatus.OK);
    }
}
