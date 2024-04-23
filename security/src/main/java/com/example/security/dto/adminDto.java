package com.example.security.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class adminDto {
    private String firstname;
        private String lastname;
        private String username;
        @Size(min=5,max=10,message = "invalid password!")
        private String password;
        private String repeatPassword;

}
