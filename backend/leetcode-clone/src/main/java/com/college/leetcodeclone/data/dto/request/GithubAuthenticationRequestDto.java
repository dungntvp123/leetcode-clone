package com.college.leetcodeclone.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubAuthenticationRequestDto {
    private String authToken;
}
