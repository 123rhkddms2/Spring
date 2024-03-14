package kr.co.ch09.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/user1/list")
    public String user1List(){
        return "/user1/list";
    }

    @GetMapping("/user1/register")
    public String user1Register(){
        return "/user1/register";
    }

    @GetMapping("/user1/modify")
    public String user1Modify(){
        return "/user1/modify";
    }

    @GetMapping("/user2/list")
    public String user2List(){
        return "/user2/list";
    }

    @GetMapping("/user2/register")
    public String user2Register(){
        return "/user2/register";
    }

    @GetMapping("/user2/modify")
    public String user2Modify(){
        return "/user2/modify";
    }

    @GetMapping("/user3/list")
    public String user3List(){
        return "/user3/list";
    }

    @GetMapping("/user3/register")
    public String user3Register(){
        return "/user3/register";
    }

    @GetMapping("/user3/modify")
    public String user3Modify(){
        return "/user3/modify";
    }

    @GetMapping("/user4/list")
    public String user4List(){
        return "/user4/list";
    }

    @GetMapping("/user4/register")
    public String user4Register(){
        return "/user4/register";
    }

    @GetMapping("/user4/modify")
    public String user4Modify(){
        return "/user4/modify";
    }

    @GetMapping("/user5/list")
    public String user5List(){
        return "/user5/list";
    }

    @GetMapping("/user5/register")
    public String user5Register(){
        return "/user5/register";
    }

    @GetMapping("/user5/modify")
    public String user5Modify(){
        return "/user5/modify";
    }


}