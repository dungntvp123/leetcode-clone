package com.college.leetcodeclone.service.impl;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.common.ResponseStatus;
import com.college.leetcodeclone.data.constant.Authority;
import com.college.leetcodeclone.data.dto.request.RegisterRequestDto;
import com.college.leetcodeclone.data.dto.request.UsernamePasswordAuthenticationRequestDto;
import com.college.leetcodeclone.data.dto.response.RegisterResponseDto;
import com.college.leetcodeclone.data.dto.response.UsernamePasswordAuthenticationResponseDto;
import com.college.leetcodeclone.data.entity.Account;
import com.college.leetcodeclone.data.entity.User;
import com.college.leetcodeclone.exception.RegisterInformationConstraintViolateException;
import com.college.leetcodeclone.helper.ValidationHelper;
import com.college.leetcodeclone.repository.AccountRepository;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AccountRepository accountRepository;
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
            log.info("{}", ex);
            throw ex;
        }
        String token = jwtHelper.generateToken(requestDto.getUsername());
        return new ResponseBody<>(ResponseStatus.AUTHENTICATION_SUCCESSFUL, new UsernamePasswordAuthenticationResponseDto(token));
    }

    @Override
    @Transactional
    public ResponseBody<RegisterResponseDto> register(RegisterRequestDto requestDto) {
        Set<Authority> authorities = new HashSet<>(Arrays.asList(Authority.USER));
        List<String> validations = ValidationHelper.getViolationMessage(requestDto);
        if (validations.isEmpty()) {
            User user = User.builder()
                    .email(requestDto.getEmail())
                    .firstName(requestDto.getFirstname())
                    .lastName(requestDto.getLastname())
                    .build();

            Account account = Account.builder()
                    .username(requestDto.getUsername())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .authorities(authorities)
                    .user(user)
                    .build();

            try {
                accountRepository.save(account);
            } catch (Exception exception) {
                validations.add(exception.getMessage());
            }
        }
        if (!validations.isEmpty()) {
            try {
                String message = (new ObjectMapper()).writeValueAsString(validations);
                throw new RegisterInformationConstraintViolateException(message);
            } catch (JsonProcessingException ignored) {

            }
        }
        String token = jwtHelper.generateToken(requestDto.getUsername());
        return new ResponseBody<>(ResponseStatus.REGISTRATION_SUCCESSFUL, new RegisterResponseDto(token));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }
}
