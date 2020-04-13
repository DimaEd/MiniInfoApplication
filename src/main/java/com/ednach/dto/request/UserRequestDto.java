package com.ednach.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class UserRequestDto {

    private Long id;

    private String name;

    private String password;

    private String email;

    private Set<Long> roleId;

    public UserRequestDto(Long id, String name, String email, Set<Long> roleId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleId = roleId;
    }

    public UserRequestDto() {
    }
}
