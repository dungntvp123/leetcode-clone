package com.college.leetcodeclone.controller;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.data.dto.request.RegisterRequestDto;
import com.college.leetcodeclone.data.dto.request.UsernamePasswordAuthenticationRequestDto;
import com.college.leetcodeclone.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/v1/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UsernamePasswordAuthenticationRequestDto requestDto) {
        log.info("(authenticate)");
        ResponseBody body = authService.authenticate(requestDto);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/v1/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto requestDto) {
        log.info("(register)");
        ResponseBody body = authService.register(requestDto);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/v1/demo")
    public ResponseEntity<?> demo() {
        return ResponseEntity.ok("ok");
    }
}
