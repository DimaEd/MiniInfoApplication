package com.ednach.controller;

import com.ednach.dto.request.ProducerRequestDto;
import com.ednach.dto.request.UserRequestDto;
import com.ednach.dto.response.ProducerResponseDto;
import com.ednach.dto.response.RoleResponseDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.Producer;
import com.ednach.model.Role;
import com.ednach.service.ProducerService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private Mapper mapper;
    private ProducerService producerService;

    public ProducerController(Mapper mapper, ProducerService producerService) {
        this.mapper = mapper;
        this.producerService = producerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProducerResponseDto>> getAll() {
        final List<Producer> producers = producerService.findAll();
        final List<ProducerResponseDto> producerResponseDtoList = producers.stream()
                .map((producer) -> mapper.map(producer, ProducerResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(producerResponseDtoList, HttpStatus.OK);
    }
        @RequestMapping(value = "/{companyName}", method = RequestMethod.GET)
    public ResponseEntity<ProducerResponseDto> getOne(@PathVariable String companyName) {
        final ProducerResponseDto producerResponseDto = mapper.map(producerService.findProducerByCompanyName(companyName), ProducerResponseDto.class);
        return new ResponseEntity<>(producerResponseDto, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProducerResponseDto> save(@Valid @RequestBody ProducerRequestDto producerRequestDto) {
        producerRequestDto.setId(null);
        final ProducerResponseDto producerResponseDto = mapper.map(producerService.save(mapper.map(producerRequestDto,Producer.class)), ProducerResponseDto.class);
        return new ResponseEntity<>(producerResponseDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProducerResponseDto> update(@Valid @RequestBody ProducerRequestDto producerRequestDto, @PathVariable Long id) {
//        if (!Objects.equals(id, roleDto.getId())) {
//            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
//        }
        final ProducerResponseDto producerResponseDto = mapper.map(producerService.update(mapper.map(producerRequestDto, Producer.class)), ProducerResponseDto.class);
        return new ResponseEntity<>(producerResponseDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        producerService.deleteById(id);
    }

}
