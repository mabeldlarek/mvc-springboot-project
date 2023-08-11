package com.mabeldlarek.web.controller;


import com.mabeldlarek.web.dtos.ClubDTO;
import com.mabeldlarek.web.models.Club;
import com.mabeldlarek.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model){
        List<ClubDTO> clubs = clubService.findAll();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }


    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDTO club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId, @ModelAttribute("club") ClubDTO club) {
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs2")
    public ResponseEntity<List<ClubDTO>> getAllClubs(){
        return new ResponseEntity<>(clubService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ClubDTO> createClub(@RequestBody Club club){
        return new ResponseEntity<>(clubService.createClub(club), HttpStatus.OK);
    }

   /* @PutMapping("/{id}/update")
    public ResponseEntity<ClubDTO> updateClub(@RequestBody ClubDTO club, @PathVariable Long id){
        return new ResponseEntity<>(clubService.updateClub(club, id), HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteClub(@PathVariable Long id){
        return new ResponseEntity<>(clubService.deleteClub(id), HttpStatus.OK);
    }
}