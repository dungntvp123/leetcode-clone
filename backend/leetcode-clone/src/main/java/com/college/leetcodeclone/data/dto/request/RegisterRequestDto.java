package com.college.leetcodeclone.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "construct")
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "username is mandatory")
    @Size(min = 8, max = 20, message = "username must be between 8 and 20 characters")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "username can contain only word characters and numbers")
    private String username;
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, message = "password must more than 8 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$",
            message = "password must contain at least one uppercase letter," +
                    " one lowercase letter, one number and one special character")
    private String password;
    @Size(max = 64, message = "email must less than 64 characters")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email must in email format")
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "user first name is mandatory")
    @Size(min = 2, max = 20, message = "user first name must be between 2 and 20 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "user first name can contain only word characters and numbers")
    private String firstname;
    @NotBlank(message = "user last name is mandatory")
    @Size(min = 2, max = 20, message = "user first name must be between 2 and 20 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "user last name can contain only word characters and numbers")
    private String lastname;
}
