package com.ednach.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignInRequestDto {
    private String username;
    private String password;
}
