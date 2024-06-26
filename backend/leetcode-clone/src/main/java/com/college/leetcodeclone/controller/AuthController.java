package com.college.leetcodeclone.controller;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.data.dto.request.OtpRequestDto;
import com.college.leetcodeclone.data.dto.request.RegisterRequestDto;
import com.college.leetcodeclone.data.dto.request.ResetPasswordRequestDto;
import com.college.leetcodeclone.data.dto.request.UsernamePasswordAuthenticationRequestDto;
import com.college.leetcodeclone.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @PostMapping("/v1/account-verify")
    public ResponseEntity<?> accountVerify(@RequestParam String verifyToken) {
        log.info("(account-verify)");
        ResponseBody body = authService.verifyToken(verifyToken);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/v1/otp")
    public ResponseEntity<?> getOtp(@RequestBody OtpRequestDto requestDto) {
        log.info("(otp)");
        ResponseBody body = authService.getOtp(requestDto);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/v1/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequestDto requestDto) {
        log.info("(reset-password)");
        UserDetails userDetails = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        ResponseBody body = authService.resetPassword(requestDto, userDetails);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/v1/demo")
    public ResponseEntity<?> demo() {
        return ResponseEntity.ok("ok");
    }
}
