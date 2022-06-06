package com.viktor.task.shoes.manager.web.controllers;

import com.viktor.task.shoes.manager.web.dto.ResponseShoes;
import com.viktor.task.shoes.manager.web.model.Shoes;
import com.viktor.task.shoes.manager.web.service.ShoesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/**", maxAge = 3600)
@RestController
@RequestMapping("/rest/shoes")
public class ShoesRestController {
    private static final Logger LOG = LoggerFactory.getLogger(ShoesRestController.class);

    private ShoesService shoesService;

    @Autowired
    public ShoesRestController(ShoesService shoesService) {
        this.shoesService = shoesService;
    }

//List 
    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getShoesList(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name == "") {
           return findPaginated(1, 5, "id", "asc");
           
        } else {
        final List<Shoes> shoesList = this.shoesService.findByName(name);
        LOG.info("Shoes Found: '{}'", shoesList);
        return ResponseEntity.ok(shoesList);
        }
       
    }

//Create
    @PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createShoes(@RequestBody Shoes shoes) {
        LOG.info("About to create new shoes: '{}'", shoes);
        try {
            this.shoesService.saveShoes(shoes);
            return ResponseEntity.ok(shoes );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
        }
    }

//Delete
    @RequestMapping(name = "delete", value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteShoes(@PathVariable("id") Integer id) {
        Object deletedSh ;
        if (null != id) {
            deletedSh= this.shoesService.findById( id);
            this.shoesService.deleteShoes(id);
            LOG.info("Delete is successfull");
            return  ResponseEntity.status(HttpStatus.OK).body(deletedSh);
        }
        return ResponseEntity.ok("Cannot delete shoes with null id");
    }
//Edit
    @RequestMapping(name = "edit", value = "{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
        MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
public ResponseEntity<?> editShoes(@PathVariable("id") Integer id, @RequestBody Shoes shoes) {

    if (null != id) {
        Optional<Shoes> shoesId = this.shoesService.findById(id);
        LOG.info("shoes to edit: '{}'", shoesId);
        Shoes shoe = shoesId.get();
        shoe.setName(shoes.getName());
        shoe.setColor(shoes.getColor());
        shoe.setQuantity(shoes.getQuantity());
        shoe.setWeightKg(shoes.getWeightKg());
        shoes.setId(shoe.getId());
        this.shoesService.saveShoes(shoe);
        LOG.info("edited shoes: '{}'", shoe);
        return ResponseEntity.ok(shoe);
    } else {
        return ResponseEntity.ok("Cannot edit shoes with null id");
    }
}

//Info
@GetMapping(name="info",value = "{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getShoesInfo(@PathVariable("id") Integer id){
        if (null != id) {
            Optional<Shoes> shoesId = this.shoesService.findById(id);
            LOG.info("Found shoes:",shoesId);
            return  ResponseEntity.ok(shoesId);
            
        }else{
            return ResponseEntity.ok("Cannot find shoes with null id");
        }
       
    }

//Pagination and Sorting
 @RequestMapping(value = "/page/{pageNo}/size/{pageSize}", produces = { MediaType.APPLICATION_JSON_VALUE },
//   consumes = {MediaType.APPLICATION_JSON_VALUE }, 
  method = RequestMethod.GET)
public ResponseEntity<?> findPaginated(@PathVariable(value = "pageNo") Integer pageNo,
        @PathVariable(value = "pageSize") Integer pageSize,@RequestParam(value="sortField",required = false) String sortField,
        @RequestParam(value="sortDir",required = false) String sortDirection) {
            if (pageSize == null) {
                pageSize = 5;   
            }
    Page<Shoes> page = shoesService.listPaginated(pageNo, pageSize,sortField,sortDirection);
    List<Shoes> listShoes = page.getContent();  
    return  ResponseEntity.ok(new ResponseShoes(listShoes,page.getTotalPages(),page.getTotalElements()));
}

// @PostMapping(value = "/page/{pageNo}/size/{pageSize}")
// public String findPaginatedSize(@RequestParam(value = "pageSize") Integer pageSize, Model model) {
//     if (pageSize == null) {
//         pageSize = 5;
//     }
//     model.addAttribute("pageSize", pageSize);
//   return findPaginated(1, pageSize,"name","asc", model);
// }
}