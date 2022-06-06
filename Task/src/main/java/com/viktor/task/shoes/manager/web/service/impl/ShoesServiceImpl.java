package com.viktor.task.shoes.manager.web.service.impl;


import com.viktor.task.shoes.manager.web.service.ShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.viktor.task.shoes.manager.web.dao.ShoesDao;
import com.viktor.task.shoes.manager.web.model.Shoes;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Defuslt implementation of {@link ShoesService} service.
 */
@Service
public class ShoesServiceImpl implements ShoesService {
    @Autowired
    private final ShoesDao shoesDao;

    @Autowired
    public ShoesServiceImpl(ShoesDao shoesDao) {
        this.shoesDao = shoesDao;
    }

    @Override
    @Transactional
    public void saveShoes(Shoes shoes) {
        this.shoesDao.save(shoes);
    }

    @Override
    @Transactional
    public void deleteShoes(Integer id) {
        this.shoesDao.deleteById(id);
    }

    @Override
    public Optional<Shoes> findById(Integer id) {
        return this.shoesDao.findById(id);
    }

    @Override
    public List<Shoes> findByName(String name) {
        return shoesDao.findByName(name);
    }
    @Override
    public List<Shoes> findAll() {
        return this.shoesDao.findAll();
    }
    @Override
    public Page<Shoes> listPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
        Sort.by(sortField).descending();
         Pageable pageable=PageRequest.of(pageNo-1, pageSize,sort);
         return this.shoesDao.findAll(pageable);
    }
   
    // @Override
    // public List<Shoes> findAll() {
//     final List<Shoes> shoesFromDb = this.shoesDao.findAll();
//        final List<ShoesDto> shoesList = new ArrayList<>();
//        for (Shoes shoes : shoesFromDb) {
//            ShoesDto shoesDto = toDto(shoes);
//            shoesList.add(shoesDto);
//        }
//        return shoesList;

   

        // TODO: The same as above
        // return this.shoesDao.findAll()
        //         .stream()
        //         .map(this::toDto)
        //         .collect(Collectors.toList());
    // }

   

   

    // private ShoesDto toDto(Shoes shoes) {
    //     ShoesDto newShoesDto = new ShoesDto();
    //     newShoesDto.setId(shoes.getId());
    //     newShoesDto.setName(shoes.getName());
    //     newShoesDto.setColor(shoes.getColor());
    //     newShoesDto.setQuantity(shoes.getQuantity());
    //     newShoesDto.setWeightKg(shoes.getWeightKg());
    //     return newShoesDto;
    // }

//    private ShoesDto toDto2(Shoes shoes, boolean param) {
//        return new ShoesDto();
//    }

    // private Shoes fromDto(ShoesDto shoesDto) {
    //     Shoes shoes = new Shoes();
    //     shoes.setName(shoesDto.getName());
    //     shoes.setColor(shoesDto.getColor());
    //     shoes.setQuantity(shoesDto.getQuantity());
    //     shoes.setWeightKg(shoesDto.getWeightKg());
    //     return shoes;
    // }

   
}
