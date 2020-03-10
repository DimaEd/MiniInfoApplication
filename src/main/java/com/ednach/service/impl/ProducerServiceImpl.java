package com.ednach.service.impl;

import com.ednach.model.Producer;
import com.ednach.repository.ProducerRepository;
import com.ednach.service.ProducerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {
    private ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Producer findById(Long id) {
        return producerRepository.findById(id).orElseThrow(()-> new RuntimeException("What is hapened"));
    }

    @Override
    public Producer findProducerByCompanyName(String companyName) {
        return producerRepository.findProducerByCompanyName(companyName);
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepository.saveAndFlush(producer);
    }

    @Override
    public Producer update(Producer producer) {
        return producerRepository.saveAndFlush(producer);
    }

    @Override
    public void delete(Producer producer) {
        producerRepository.delete(producer);
        System.out.println("Object was deleted");
    }

    @Override
    public void deleteById(Long id) {
        producerRepository.deleteById(id);
        System.out.println("Object was deleted by id");
    }
}
