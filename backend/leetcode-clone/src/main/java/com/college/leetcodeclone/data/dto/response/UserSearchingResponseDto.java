package com.college.leetcodeclone.data.dto.response;

import com.college.leetcodeclone.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSearchingResponseDto {
    private String name;
    private String email;
    private String image;

    public UserSearchingResponseDto(User user) {
        this.name = user.getName();
        this.image = "#";
        this.email = user.getAccount().getEmail();
    }
}
