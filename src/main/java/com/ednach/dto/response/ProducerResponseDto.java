package com.ednach.dto.response;

import com.ednach.dto.request.ProductRequestDto;
import com.ednach.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
@Getter
@Setter
public class ProducerResponseDto {
    private Long id;
    private String companyName;
    private String email;
    private String country;
    private Set<ProductRequestDto> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProducerResponseDto that = (ProducerResponseDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(country, that.country) &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, email, country, products);
    }
}
