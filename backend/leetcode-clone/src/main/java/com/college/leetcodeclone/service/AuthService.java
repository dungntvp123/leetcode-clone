package com.college.leetcodeclone.service;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.data.dto.request.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    ResponseBody<?> authenticate(UsernamePasswordAuthenticationRequestDto requestDto);

    ResponseBody<?> register(RegisterRequestDto requestDto);

    ResponseBody<?> verifyToken(String verifyToken);

    ResponseBody<?> resetPassword(ResetPasswordRequestDto requestDto, UserDetails userDetails);

    ResponseBody<?> googleAuthenticate(GoogleAuthenticationRequestDto requestDto);

    ResponseBody<?> githubAuthenticate(GithubAuthenticationRequestDto requestDto);

    ResponseBody<?> getOtp(OtpRequestDto requestDto);
}
