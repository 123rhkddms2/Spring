package kr.co.ch07.controller;

import kr.co.ch07.dto.User5DTO;
import kr.co.ch07.service.User5Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class User5Controller {

    private final User5Service service;

    @GetMapping("/user5/list")
    public String list(Model model){

        List<User5DTO> users = service.selectUser5s();
        log.info(users.toString());

        model.addAttribute("users", users);

        return "/user5/list";
    }

    @GetMapping("/user5/register")
    public String register(){
        return "/user5/register";
    }

    @PostMapping("/user5/register")
    public String register(User5DTO user5DTO){
        log.info(user5DTO.toString());

        service.insertUser5(user5DTO);

        return "redirect:/user5/list";
    }

    @GetMapping("/user5/modify")
    public String modify(String seq, Model model){
        User5DTO user5DTO = service.selectUser5(seq);
        log.info(user5DTO.toString());

        model.addAttribute(user5DTO);
        return "/user5/modify";
    }

    @PostMapping("/user5/modify")
    public String modify(User5DTO user5DTO){
        log.info(user5DTO.toString());

        service.updateUser5(user5DTO);
        return "redirect:/user5/list";
    }

    @GetMapping("/user5/delete")
    public String delete(String seq){
        log.info(seq);

        service.deleteUser5(seq);
        return "redirect:/user5/list";
    }

}