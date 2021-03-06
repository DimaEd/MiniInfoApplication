package com.ednach.service;

import com.ednach.model.Producer;

import java.util.List;

public interface ProducerService {
    List<Producer> findAll();

    List<Producer> findProducerByCompanyName(String companyName);

    Producer findByCompanyName(String companyName);

    Producer findById(Long id);

    Producer save(Producer producer);

    Producer update(Producer producer);

    void delete(Producer producer);

    void deleteById(Long id);
}
