package com.ednach.dto.response;

import com.ednach.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class ProducerResponseDto {
    private Long id;
    private String companyName;
    private String email;
    private String country;
    private Set<ProductResponseDto> products;

}
