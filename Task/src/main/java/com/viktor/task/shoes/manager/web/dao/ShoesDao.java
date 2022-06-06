package com.viktor.task.shoes.manager.web.dao;

import com.viktor.task.shoes.manager.web.model.Shoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoesDao extends JpaRepository<Shoes, Integer> {

    // @Override
    // void deleteById(Long id);

    // void addShoes(Shoes shoes);
    // void updateShoes(Shoes shoes);
    // void deleteShoesByID(int id);
    // Shoes findByID(int id);

    /*
     * @Override Optional<Shoes> findById(Long id);
     */

    @Override
    List<Shoes> findAll();

    List<Shoes> findByName(String name);

}
