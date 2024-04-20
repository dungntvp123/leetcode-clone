package com.college.leetcodeclone.service;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.data.dto.request.RegisterRequestDto;
import com.college.leetcodeclone.data.dto.request.UsernamePasswordAuthenticationRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    public ResponseBody authenticate(UsernamePasswordAuthenticationRequestDto requestDto);

    public ResponseBody register(RegisterRequestDto requestDto);

    public ResponseBody verifyToken(String verifyToken);
}
