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

    private Set<Long> roleIds;

    public UserRequestDto(Long id, String name,String password, String email, Set<Long> roleIds) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roleIds = roleIds;
    }

    public UserRequestDto() {
    }
    public UserRequestDto(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
