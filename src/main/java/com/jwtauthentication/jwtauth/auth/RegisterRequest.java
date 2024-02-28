package com.jwtauthentication.jwtauth.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {


    String firstname;

    String lastname;

    String email;

    String password;

}
