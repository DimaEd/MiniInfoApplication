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

}
