package com.ednach.repository;

import com.ednach.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    //    @Query("select c from Child c join fetch c.parent where c.id = :id")
//    Child findByIdFetchParent(@Param("id") Long id);
//SELECT d.name, e.name, e.email, e.address FROM department d LEFT JOIN employee e ON d.id = e.dept_id;
//@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles ORDER BY u.id")
//List<User> findAllWithRoles();
    @Query("select DISTINCT p from Producer p join fetch p.products")
    List<Producer> findAll();

  //  @Query("SELECT p FROM producer p JOIN products_producers pp on p.id = pp.producer_id where p.id =: id")
    Producer findProducerByCompanyName(String companyName);

}
