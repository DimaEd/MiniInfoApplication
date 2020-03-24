package unit.controller;

import com.ednach.controller.ProducerController;
import com.ednach.controller.UserController;
import com.ednach.dto.request.ProducerRequestDto;
import com.ednach.dto.request.UserRequestDto;
import com.ednach.dto.response.ProducerResponseDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.Producer;
import com.ednach.model.User;
import com.ednach.service.impl.ProducerServiceImpl;
import com.ednach.service.impl.UserServiceImpl;
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
class ProducerControllerUnitTest {

    @InjectMocks
    ProducerController producerController;

    @Mock
    ProducerServiceImpl producerService;

    @Spy
    Mapper mapper = new DozerBeanMapper();

    final Producer producer = new Producer();
    final ProducerResponseDto producerResponseDto = new ProducerResponseDto();

    @Test
    void getAll() {
        List<ProducerResponseDto> producerResponseDtoList = singletonList(producerResponseDto);
        ResponseEntity<List<ProducerResponseDto>> producerResponseDtoResponseEntity = new ResponseEntity<>(producerResponseDtoList, HttpStatus.OK);
        List<Producer> producers = singletonList(producer);
        when(producerService.findAll()).thenReturn(producers);
        assertEquals(producerController.getAll(), producerResponseDtoResponseEntity);
    }

    @Test
    void getName() {
        ResponseEntity<ProducerResponseDto> producerResponseDtoResponseEntity = new ResponseEntity(producerResponseDto, HttpStatus.OK);
        when(producerService.findProducerByCompanyName(any(String.class))).thenReturn(producer);
        assertEquals(producerController.getName("Nokia"), producerResponseDtoResponseEntity);
    }

    @Test
    void save() {
        ResponseEntity<ProducerResponseDto> producerResponseDtoResponseEntity = new ResponseEntity(producerResponseDto, HttpStatus.OK);
        ProducerRequestDto producerRequestDto = new ProducerRequestDto();
        when(producerService.save(any())).thenReturn(producer);
        assertEquals(producerController.save(producerRequestDto), producerResponseDtoResponseEntity);
    }

    @Test
    void update() {
        ResponseEntity<ProducerResponseDto> producerResponseDtoResponseEntity = new ResponseEntity(producerResponseDto, HttpStatus.OK);
        ProducerRequestDto producerRequestDto = new ProducerRequestDto();
        when(producerService.update(any())).thenReturn(producer);
        assertEquals(producerController.update(producerRequestDto, 1L), producerResponseDtoResponseEntity);
    }

    @Test
    void delete() {
        doNothing().when(producerService).deleteById(any(Long.class));
        assertDoesNotThrow(() -> producerController.delete(1L));
    }

}
