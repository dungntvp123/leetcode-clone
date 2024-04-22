package com.college.leetcodeclone.service;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.data.dto.request.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthService extends UserDetailsService {
    public ResponseBody authenticate(UsernamePasswordAuthenticationRequestDto requestDto);

    public ResponseBody register(RegisterRequestDto requestDto);

    public ResponseBody verifyToken(String verifyToken);

    public ResponseBody resetPassword(ResetPasswordRequestDto requestDto, UserDetails userDetails);

    public ResponseBody googleAuthenticate(GoogleAuthenticationRequestDto requestDto);

    public ResponseBody githubAuthenticate(GithubAuthenticationRequestDto requestDto);
}
