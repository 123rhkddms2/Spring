package kr.co.sboard.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MyController {

    private final UserService userService;

    @GetMapping("/my/setting")
    public String setting() {
        return "/my/setting";
    }
}
