package com.ednach.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RoleResponseDto {
    private Long id;

    private String roleName;

    private Set<UserResponseDto> users;

}
