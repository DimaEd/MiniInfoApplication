package com.ednach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class RoleDto {
    private Long id;
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id) &&
                Objects.equals(roleName, roleDto.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }
}
