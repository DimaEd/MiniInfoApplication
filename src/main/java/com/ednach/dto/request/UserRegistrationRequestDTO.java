package com.ednach.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRegistrationRequestDTO {

    private String name;

    private String password;

    private Set<String> roles;
}