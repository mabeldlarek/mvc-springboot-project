package com.mabeldlarek.web.controller;

import com.mabeldlarek.web.dto.RegistrationDto;
import com.mabeldlarek.web.models.UserEntity;
import com.mabeldlarek.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user, BindingResult result, Model model){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        UserEntity existingUsername = userService.findByUsername(user.getUsername());
        if(existingUsername != null && existingUsername.getEmail() != null && !existingUsername.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUSer(user);
        return "redirect:/clubs?sucess";
    }
}
