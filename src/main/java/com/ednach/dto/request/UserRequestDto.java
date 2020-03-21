package com.ednach.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequestDto {
    private Long id;
    private String name;
    private String email;
    private Long roleId;

    public UserRequestDto( Long id,String name, String email, Long roleId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleId = roleId;
    }

    public UserRequestDto() {
    }
}
