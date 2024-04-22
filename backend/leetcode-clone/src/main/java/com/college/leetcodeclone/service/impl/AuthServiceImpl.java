package com.college.leetcodeclone.service.impl;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.common.ResponseStatus;
import com.college.leetcodeclone.data.constant.Authority;
import com.college.leetcodeclone.data.dto.request.*;
import com.college.leetcodeclone.data.dto.response.AccountVerificationResponseDto;
import com.college.leetcodeclone.data.dto.response.RegisterResponseDto;
import com.college.leetcodeclone.data.dto.response.UsernamePasswordAuthenticationResponseDto;
import com.college.leetcodeclone.data.entity.Account;
import com.college.leetcodeclone.data.entity.AccountVerifyToken;
import com.college.leetcodeclone.data.entity.User;
import com.college.leetcodeclone.exception.AccountVerifyTokenExpiredException;
import com.college.leetcodeclone.exception.RegisterInformationConstraintViolateException;
import com.college.leetcodeclone.exception.ResetPasswordInformationConstraintViolateException;
import com.college.leetcodeclone.helper.ValidationHelper;
import com.college.leetcodeclone.repository.AccountRepository;
import com.college.leetcodeclone.repository.AccountVerifyTokenRepository;
import com.college.leetcodeclone.service.AuthService;
import com.college.leetcodeclone.helper.JwtHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountVerifyTokenRepository accountVerifyTokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public ResponseBody<UsernamePasswordAuthenticationResponseDto> authenticate(UsernamePasswordAuthenticationRequestDto requestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())
            );
        } catch (Exception ex) {
            throw ex;
        }
        String token = jwtHelper.generateToken(requestDto.getUsername());
        return new ResponseBody<>(ResponseStatus.AUTHENTICATION_SUCCESSFUL, new UsernamePasswordAuthenticationResponseDto(token));
    }

    @Override
    @Transactional
    public ResponseBody<RegisterResponseDto> register(RegisterRequestDto requestDto) {
        Set<Authority> authorities = new HashSet<>(Arrays.asList(Authority.USER));
        List<String> violations = ValidationHelper.getViolationMessage(requestDto);
        String randToken = UUID.randomUUID().toString();
        if (accountRepository.existsByUsername(requestDto.getUsername())) {
            violations.add("Username must be unique");
        }
        if (accountRepository.existsByEmail(requestDto.getEmail())) {
            violations.add("Email must be unique");
        }
        if (!violations.isEmpty()) {
            try {
                String message = (new ObjectMapper()).writeValueAsString(violations);
                throw new RegisterInformationConstraintViolateException(message);
            } catch (JsonProcessingException ignored) {
            }
        }

        User user = User.builder()
                .name(requestDto.getFirstname() + " " + requestDto.getLastname())
                .build();

        AccountVerifyToken verifyToken = AccountVerifyToken.builder()
                .description(randToken)
                .expiration(new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000))
                .build();

        Account account = Account.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .authorities(authorities)
                .isEnable(false)
                .user(user)
                .accountVerifyToken(verifyToken)
                .build();

        accountRepository.save(account);


        return new ResponseBody<>(ResponseStatus.REGISTRATION_SUCCESSFUL, new RegisterResponseDto(randToken));
    }

    @Override
    @Transactional
    public ResponseBody<?> verifyToken(String verifyToken) {
        AccountVerifyToken accountVerifyToken = accountVerifyTokenRepository
                .findByDescription(verifyToken).get();
        if (accountVerifyToken.isExpired()) {
            throw new AccountVerifyTokenExpiredException();
        }
        Account account = accountVerifyToken.getAccount();
        String token = jwtHelper.generateToken(account);
        account.setEnable(true);
        accountVerifyToken.setAccount(account);
        accountVerifyToken.setExpiration(new Timestamp(System.currentTimeMillis()));
        accountVerifyTokenRepository.save(accountVerifyToken);

        return new ResponseBody<>(ResponseStatus.ACCOUNT_VERIFICATION_SUCCESSFUL, new AccountVerificationResponseDto(token));
    }

    @Override
    @Transactional
    public ResponseBody<?> resetPassword(ResetPasswordRequestDto requestDto, UserDetails userDetails) {
        List<String> violations = ValidationHelper.getViolationMessage(requestDto);
        if (!violations.isEmpty()) {
            try {
                String message = (new ObjectMapper()).writeValueAsString(violations);
                throw new ResetPasswordInformationConstraintViolateException(message);
            } catch (JsonProcessingException ignored) {
            }
        }

        Account account = accountRepository.findByUsername(userDetails.getUsername()).get();
        account.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        accountRepository.save(account);
        return new ResponseBody<>(ResponseStatus.RESET_PASSWORD_SUCCESSFUL);
    }

    @Override
    public ResponseBody googleAuthenticate(GoogleAuthenticationRequestDto requestDto) {

        return null;
    }

    @Override
    public ResponseBody githubAuthenticate(GithubAuthenticationRequestDto requestDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username).orElse(null);
    }
}
