package com.ednach.controller;

import com.ednach.dto.request.ProductRequestDto;
import com.ednach.dto.request.UserRequestDto;
import com.ednach.dto.response.ProductResponseDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.Product;
import com.ednach.model.Role;
import com.ednach.model.User;
import com.ednach.service.ProductService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private Mapper mapper;
    private ProductService productService;

    public ProductController(Mapper mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        final List<Product> products = productService.findAll();
        final List<ProductResponseDto> productResponseDtoList = products.stream()
                .map((product) -> mapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<UserResponseDto> getOne(@PathVariable Long id) {
//        final UserResponseDto userResponseDto = mapper.map(userService.findById(id), UserResponseDto.class);
//        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
//    }
    @RequestMapping(value = "/{productName}", method = RequestMethod.GET)
    public ResponseEntity<ProductResponseDto> getName(@PathVariable String productName) {
        final ProductResponseDto productResponseDto = mapper.map(productService.findByProductName(productName), ProductResponseDto.class);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto productRequestDto) {
        productRequestDto.setId(null);
        final ProductResponseDto productResponseDto = mapper.map(productService.save(getProduct(productRequestDto)), ProductResponseDto.class);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductResponseDto> update(@Valid @RequestBody ProductRequestDto productRequestDto, @PathVariable Long id) {
        final ProductResponseDto productResponseDto = mapper.map(productService.update(getProduct(productRequestDto)), ProductResponseDto.class);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    private Product getProduct(ProductRequestDto productRequestDto) {
        final Product product = mapper.map(productRequestDto, Product.class);
        final User user = new User();
        user.setId(productRequestDto.getUserId());
        product.setUser(user);
        return product;


    }
}
