package com.college.leetcodeclone.controller;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.data.dto.request.UserSearchingCriteriaRequestDto;
import com.college.leetcodeclone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/v1/load")
    public ResponseEntity<?> loadUser(UserSearchingCriteriaRequestDto requestDto) {
        ResponseBody body = userService.loadUser(requestDto);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/profile/me")
    public ResponseEntity<?> myProfile() {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        ResponseBody body = userService.loadUser(username);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/v1/profile")
    public ResponseEntity<?> userProfile(@RequestParam String username) {
        ResponseBody body = userService.loadUser(username);
        return ResponseEntity.ok(body);
    }
}
