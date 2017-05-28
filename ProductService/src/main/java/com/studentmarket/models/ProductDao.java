package com.studentmarket.models;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

/**
 * Created by Andreas on 5/26/2017.
 */

@Transactional
public interface ProductDao extends CrudRepository<Product, Long>{

    public Product findByTitle(String title);

}
