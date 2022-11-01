package com.ssafy.home.member.controller;

import com.ssafy.home.common.dto.ResponseDTO;
import com.ssafy.home.member.dto.LoginDTO;
import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = {"users"})
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("info")
    @ResponseBody
    public ResponseEntity<ResponseDTO> userInfo(HttpServletRequest req) {
        Member member = (Member) req.getAttribute("member");
        ResponseDTO res = new ResponseDTO();
        res.setStatus("success");
        res.setBody(member);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * @param member: 사용자 id, pw
     * @param req     : request
     * @return : body
     * @throws Exception : 암호화 관련 예외 포함
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공")
    })
    public ResponseEntity<ResponseDTO> login(
            @ApiParam(value = "member")
            @RequestBody LoginDTO member,
            HttpServletRequest req) {
        ResponseDTO res = new ResponseDTO();
        try {
            // 로그인 프로세스 추가
            Member user = memberService.login(member);
            HttpSession session = req.getSession();
            session.setAttribute("member", user);

            res.setStatus("success");
            res.setMsg("login success");
            res.setBody(user);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setStatus("fail");
            res.setErrMsg("로그인 정보가 잘못되었습니다. 다시 로그인해 주세요");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/register")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "사용자 회원가입")
    })
    public String register(
            @ApiParam(value = "member")
            @RequestBody Member member) throws Exception {
        System.out.println(member.getId());
        memberService.register(member);
        return "register ok";
    }

    @PostMapping("idchck")
    @ResponseBody
    public ResponseEntity<ResponseDTO> idCheck(
            @ApiParam(value = "userId")
            @RequestParam String userId) {
        int isId = memberService.checkId(userId);
        ResponseDTO res = new ResponseDTO();
        if (isId > 0) {
            // id 존재
            res.setStatus("fail");
            res.setErrMsg("id가 존재합니다.");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } else {
            //id 생성 가능
            res.setStatus("success");
            res.setMsg("id는 생성 가능합니다.");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
