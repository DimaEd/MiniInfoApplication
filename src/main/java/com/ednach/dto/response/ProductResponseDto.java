package com.ednach.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String productName;
    private int cost;
    private Set<ProducerResponseDto> producers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseDto that = (ProductResponseDto) o;
        return cost == that.cost &&
                Objects.equals(id, that.id) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(producers, that.producers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, cost, producers);
    }
}
