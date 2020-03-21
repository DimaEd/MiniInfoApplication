package unit.service;

import com.ednach.model.Product;
import com.ednach.model.Role;
import com.ednach.model.User;
import com.ednach.repository.ProductRepository;
import com.ednach.service.UserService;
import com.ednach.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserService userService;

    final Product product = new Product();
    final Role role = new Role(1L,"client");
    final User user = new User(1L,"Tom","tom@mail.ru",role);


    @Test
    public void testFindAll() {
        final List<Product> productList = Collections.singletonList(new Product());
        when(productRepository.findAll()).thenReturn(productList);
        assertEquals(productService.findAll(), productList);
    }

    @Test
    public void testFindById() {
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        assertEquals(productService.findById(1L), product);
    }
    @Test
    public void testSave() {
        final Product product = new Product();
        product.setUser(user);
        when(productRepository.saveAndFlush(product)).thenReturn(product);
        when(userService.findById(1L)).thenReturn(user);
        assertEquals(productService.save(product), product);
    }

    @Test
    public void testUpdate() {
        product.setId(1L);
        product.setUser(user);
        when(productRepository.saveAndFlush(product)).thenReturn(product);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(userService.findById(1L)).thenReturn(user);
        assertEquals(productService.update(product), product);
    }
    @Test
    public void testDelete() {
        product.setId(1L);
        doNothing().when(productRepository).delete(product);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertDoesNotThrow(() -> productService.delete(product));
    }

    @Test
    public void testDeleteById() {
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> productService.deleteById(1L));
    }
}
