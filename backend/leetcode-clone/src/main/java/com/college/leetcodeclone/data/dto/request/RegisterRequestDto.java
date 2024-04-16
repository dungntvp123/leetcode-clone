package com.college.leetcodeclone.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "construct")
@NoArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
}
