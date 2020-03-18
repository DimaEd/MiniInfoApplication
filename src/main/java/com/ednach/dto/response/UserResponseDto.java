package com.ednach.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id ;
    private String name;
    public UserResponseDto(Long id , String name){
        this.id = id;
        this.name = name;
    }

    public UserResponseDto() {
    }
}
