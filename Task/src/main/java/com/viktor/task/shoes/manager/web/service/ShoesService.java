package com.viktor.task.shoes.manager.web.service;

import com.viktor.task.shoes.manager.web.model.Shoes;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Interface for operations with shoes
 */
@Service
public interface ShoesService {
    void saveShoes(Shoes shoes);

   

    /**
     * Remove particular shoes from database.
     * 
     * @param id id of the shoes to be removed
     */
    void deleteShoes(Integer id);

    Optional<Shoes> findById(Integer id);

    List<Shoes> findAll();

    List<Shoes> findByName(String name);

    Page<Shoes>listPaginated(int pageNo,int pageSize,String sortField,String sortDirection);

}
