package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 사용자 로그인 페이지를 요청
    @GetMapping("/user/login")
    public String login(@ModelAttribute("success") String success){
        // 매개변수 success에 @ModelAttribute 선언으로 View 참조할 수 있음

        return "/user/login";
    }

    // 이용약관 페이지를 요청
    @GetMapping("/user/terms")
    public String terms(Model model){

        TermsDTO termsDTO = userService.selectTerms();
        model.addAttribute(termsDTO);

        return "/user/terms";
    }

    // 사용자 등록 페이지를 요청
    @GetMapping("/user/register")
    public String register(){
        return "/user/register";
    }

    // 사용자 등록을 처리
    // 등록 성공 시 로그인 페이지로 리다이렉트
    @PostMapping("/user/register")
    public String register(HttpServletRequest req, UserDTO userDTO){

        String regip = req.getRemoteAddr();
        userDTO.setRegip(regip);

        log.info(userDTO.toString());

        userService.insertUser(userDTO);

        return "redirect:/user/login?success=200";
    }

    // 이메일 전송확인, 중복 확인(register)
    @ResponseBody
    @GetMapping("/user/{type}/{value}")
    public ResponseEntity<?> checkUser(HttpSession session,
                                       @PathVariable("type")  String type,
                                       @PathVariable("value") String value){


        log.info("type : " + type);
        log.info("value : " + value);

        int count = userService.selectCountUser(type, value);
        log.info("count : " + count);

        // 중복 없으면 이메일 인증코드 발송
        if(count == 0 && type.equals("email")){
            log.info("email : " + value);
            userService.sendEmailCode(session, value);
        }

        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", count);

        return ResponseEntity.ok().body(resultMap);
    }

    // 이메일 전송확인(findId, findPassword)
    @ResponseBody
    @GetMapping("/email/{code}")
    public ResponseEntity<?> checkEmail(HttpSession session, @PathVariable("code")  String code){

        String sessionCode = (String) session.getAttribute("code");

        if(sessionCode.equals(code)){
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", true);

            return ResponseEntity.ok().body(resultMap);
        }else{
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", false);

            return ResponseEntity.ok().body(resultMap);
        }
    }

    // 이메일 인증 코드를 확인(findId) - 입력한 코드와 세션에 저장된 코드 비교, 일치여부 확인
    @ResponseBody
    @GetMapping("/user/findId/sendEmailCode/{email}")
    public ResponseEntity<?> checkUserForFindId(HttpSession session,
                                                @PathVariable("email")  String email){

        //log.info("session : " + session);
        log.info("email : " + email);

        userService.checkUserForFindId(session, email);

        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return ResponseEntity.ok().body(resultMap);
    }

    // 이메일 인증 코드를 확인(findPassword) - 입력한 코드와 세션에 저장된 코드 비교, 일치여부 확인
    @ResponseBody
    @GetMapping("/user/findPassword/sendEmailCode/{email}")
    public ResponseEntity<?> checkUserForFindPassword(HttpSession session,
                                                      @PathVariable("email")  String email){

        //log.info("session : " + session);
        log.info("email : " + email);

        userService.checkUserForFindPassword(session, email);

        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return ResponseEntity.ok().body(resultMap);
    }

    // 아이디 찾기 페이지를 요청
    @GetMapping("/user/findId")
    public String findId(){
        return "/user/findId";
    }

    // 아이디 찾기 결과를 처리
    @PostMapping("/user/findIdResult")
    public String findIdResult(UserDTO userDTO, Model model){
        UserDTO findUserId = userService.selectUserForFindId(userDTO);
        model.addAttribute("user", findUserId);
        return "/user/findIdResult";
    }

    // 비밀번호 찾기 페이지를 요청
    @GetMapping("/user/findPassword")
    public String findPassword(){
        return "/user/findPassword";
    }

    // 비밀번호 찾기 결과를 처리(비밀번호 변경화면)
    @PostMapping("/user/findPasswordChange")
    public String findPasswordChange(UserDTO userDTO, Model model){
        UserDTO findUserPassword = userService.selectUserForFindPassword(userDTO);
        model.addAttribute("user", findUserPassword);
        return "/user/findPasswordChange";
    }

    // 비밀번호 변경 결과를 처리
    @PostMapping("/user/findPasswordChange")
    public String findPasswordChange(UserDTO userDTO, HttpServletRequest req){
        userDTO.setRegip(regip);

        log.info(userDTO.toString());

        userService.updateUserChangePassword(userDTO);

        return "redirect:/user/login?success=200";
    }

}