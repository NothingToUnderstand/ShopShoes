package com.viktor.task.shoes.manager.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.viktor.task.shoes.manager.web.model.Shoes;
import com.viktor.task.shoes.manager.web.service.ShoesService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;


@Controller
@RequestMapping(value = "/shoes")
public class ShoesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoesController.class);

    private ShoesService shoesService;

    @Autowired(required = true)
    public void setShoesService(ShoesService shoesService) {
        this.shoesService = shoesService;
    }

    /*
     * List of shoes
     */
    @GetMapping(value = "")
    public String listShoes(Model model) {
       
        return findPaginated(1, 5,"name","asc", model);
    }

    /*
     * Create shoes
     */
    @PostMapping(name = "create", value = "/create")
    public String saveShoesPost(@ModelAttribute("shoes") Shoes shoes) {   
            this.shoesService.saveShoes(shoes);
            return "redirect:/shoes";
    }

    @GetMapping(name = "create", value = "/create")
    public String saveShoesGet() {
        return "shoes/createShoes.html";
    }

    /*
     * Delete shoes
     */

    @DeleteMapping(name = "delete", value = "/delete/{id}")
    public String delete(@PathVariable("id") Integer id, @ModelAttribute Shoes shoes) {
        this.shoesService.deleteShoes(id);
        return "redirect:/shoes";
    }

    /*
     * Edit shoes
     */
    @PostMapping(name = "edit", value = "/edit/{id}")
    public String editPost(@ModelAttribute Shoes shoes) {
        this.shoesService.saveShoes(shoes);
        return "redirect:/shoes";
    }

    @GetMapping(value = "edit/{id}")
    public String editGet(@PathVariable(value = "id") int id, Model model) {
        Optional<Shoes> shoes = shoesService.findById(id);
        shoes.ifPresent(value -> model.addAttribute("shoes", value));
        return "shoes/editShoes.html";
    }

    /*
     * Search shoes
     */
    @PostMapping(value = "/find")
    public String search(@RequestParam(name = "name") String name, Model model) {
        
        List<Shoes> shoes = this.shoesService.findByName(name);
        model.addAttribute("shoes", shoes);
       
        return "shoes/findShoes.html";
    }

    @GetMapping(value = "/find")
    public String find(@ModelAttribute Shoes shoes) {
        this.shoesService.findByName(shoes.getName());
        return "shoes/findShoes.html";
    }
     //View
     @GetMapping(value = "/view/{id}")
     public String view(@PathVariable(value = "id") Integer id, Model model) {
         Optional<Shoes> shoes = shoesService.findById(id);
        
         shoes.ifPresent(value -> model.addAttribute("shoes", value));
         return "/shoes/viewShoes.html";
     }
 
 
 
     //Pagination and Sorting
     @GetMapping(value = "/page/{pageNo}/size/{pageSize}")
     public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
             @PathVariable(value = "pageSize") int pageSize,@RequestParam(value="sortField",required = false) String sortField,
             @RequestParam(value="sortDir",required = false) String sortDirection, Model model) {
         Page<Shoes> page = shoesService.listPaginated(pageNo, pageSize,sortField,sortDirection);
         List<Shoes> listShoes = page.getContent();
         model.addAttribute("currentPage", pageNo);
         model.addAttribute("pageSize", pageSize); 
         model.addAttribute("totalPages", page.getTotalPages());
         model.addAttribute("totalItems", page.getTotalElements());
         model.addAttribute("listShoes", listShoes);
         model.addAttribute("sortField", sortField);
         model.addAttribute("sortDir", sortDirection);
         model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        
         return "shoes/shoes.html";
     }
 
     @PostMapping(value = "/page/{pageNo}/size/{pageSize}")
     public String findPaginatedSize(@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, Model model) {
        //  if (pageSize == null) {
        //      pageSize = 5;
        //  }
         model.addAttribute("pageSize", pageSize);
       return findPaginated(1, pageSize,"name","asc", model);
     }
 }
    
   