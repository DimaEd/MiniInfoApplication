package com.ednach.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductRequestDto {
    private Long id;
    private String productName;
    private int cost;
    private Long userId;
}
