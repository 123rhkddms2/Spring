package kr.co.ch07.controller;

import kr.co.ch07.dto.User4DTO;
import kr.co.ch07.service.User4Service;
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
public class User4Controller {

    private final User4Service service;

    @GetMapping("/user4/list")
    public String list(Model model){

        List<User4DTO> users = service.selectUser4s();
        log.info(users.toString());

        model.addAttribute("users", users);

        return "/user4/list";
    }

    @GetMapping("/user4/register")
    public String register(){
        return "/user4/register";
    }

    @PostMapping("/user4/register")
    public String register(User4DTO user4DTO){
        log.info(user4DTO.toString());

        service.insertUser4(user4DTO);

        return "redirect:/user4/list";
    }

    @GetMapping("/user4/modify")
    public String modify(String uid, Model model){
        User4DTO user4DTO = service.selectUser4(uid);
        log.info(user4DTO.toString());

        model.addAttribute(user4DTO);
        return "/user4/modify";
    }

    @PostMapping("/user4/modify")
    public String modify(User4DTO user4DTO){
        log.info(user4DTO.toString());

        service.updateUser4(user4DTO);
        return "redirect:/user4/list";
    }

    @GetMapping("/user4/delete")
    public String delete(String uid){
        log.info(uid);

        service.deleteUser4(uid);
        return "redirect:/user4/list";
    }

}