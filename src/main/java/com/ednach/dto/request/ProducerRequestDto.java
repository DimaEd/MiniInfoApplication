package com.ednach.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProducerRequestDto {
    private Long id;
    private String companyName;
    private String email;
    private String country;
}
