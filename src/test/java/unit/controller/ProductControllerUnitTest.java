package unit.controller;

import com.ednach.controller.ProductController;
import com.ednach.dto.request.ProductRequestDto;
import com.ednach.dto.response.ProductResponseDto;
import com.ednach.model.Product;
import com.ednach.service.impl.ProductServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerUnitTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @Spy
    Mapper mapper = new DozerBeanMapper();

    final Product product = new Product();
    final ProductResponseDto productResponseDto = new ProductResponseDto();

    @Test
    void getAll() {
        List<ProductResponseDto> productResponseDtoList = singletonList(productResponseDto);
        ResponseEntity<List<ProductResponseDto>> productResponseDtoResponseEntity = new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
        List<Product> products = singletonList(product);
        when(productService.findAll()).thenReturn(products);
        assertEquals(productController.getAll(), productResponseDtoResponseEntity);
    }

    @Test
    void getName() {
        ResponseEntity<ProductResponseDto> productResponseDtoResponseEntity = new ResponseEntity(productResponseDto, HttpStatus.OK);
        when(productService.findByProductName(any(String.class))).thenReturn(product);
        assertEquals(productController.getName("nokia"), productResponseDtoResponseEntity);
    }

    @Test
    void save() {
        ResponseEntity<ProductResponseDto> productResponseDtoResponseEntity = new ResponseEntity(productResponseDto, HttpStatus.OK);
        ProductRequestDto productRequestDto = new ProductRequestDto();
        when(productService.save(any())).thenReturn(product);
        assertEquals(productController.save(productRequestDto), productResponseDtoResponseEntity);
    }

    @Test
    void update() {
        ResponseEntity<ProductResponseDto> productResponseDtoResponseEntity = new ResponseEntity(productResponseDto, HttpStatus.OK);
        ProductRequestDto productRequestDto = new ProductRequestDto();
        when(productService.update(any())).thenReturn(product);
        assertEquals(productController.update(productRequestDto, 1L), productResponseDtoResponseEntity);
    }

    @Test
    void delete() {
        doNothing().when(productService).deleteById(any(Long.class));
        assertDoesNotThrow(() -> productController.delete(1L));
    }

}




