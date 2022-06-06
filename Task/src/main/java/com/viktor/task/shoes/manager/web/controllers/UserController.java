package com.viktor.task.shoes.manager.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.viktor.task.shoes.manager.web.model.User;
import com.viktor.task.shoes.manager.web.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserDetailService userDetailService;

    @Autowired(required = true)
    public void setUserService(UserDetailService userService) {
        this.userDetailService = userService;
    }

    /*
     * List of users
     */
    @GetMapping(value = "")
    public String listUsers(Model model) {

        return findPaginated(1, 5,"username","asc", model);
    }

    /*
     * registration
     */
    @GetMapping(value = "/regist")
    public String registration() {
        return "/user/registration.html";
    }

    @PostMapping(value = "/regist", name = "regist")
    public String saveUser(@ModelAttribute("userForm") User user) {

        LOGGER.info("Users:'{}'", user);
        this.userDetailService.saveUser(user);
        LOGGER.info("Created successfully");
        return "redirect:/users";
    }

    /*
     * delete
     */
    @DeleteMapping(name = "delete", value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id, @ModelAttribute User user) {
        this.userDetailService.deleteUser(id);
        LOGGER.info("Deleted successfully ");
        return "redirect:/users";
    }

    /*
     * Edit
     */
    @PostMapping(name = "edit", value = "/edit/{id}")
    public String editPost(@ModelAttribute User user) {
        this.userDetailService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping(value = "edit/{id}")
    public String editGet(@PathVariable(value = "id") Long id, Model model) {
        Optional<User> users = userDetailService.findById(id);
        LOGGER.info("FIND : '{}' ", users);
        users.ifPresent(value -> model.addAttribute("users", value));
        return "/user/userEdit.html";
    }

    /*
     * Find
     */
    @PostMapping(value = "/find")
    public String search(@RequestParam(name = "username") String username, Model model) {
       
        User users = this.userDetailService.findByUsername(username);
        model.addAttribute("users", users);
        LOGGER.info("found Users:'{}'", users);
        return "/user/userFind.html";
    }

    @GetMapping(value = "/find")
    public String find(@ModelAttribute User user) {
        this.userDetailService.findByUsername(user.getUsername());
        return "/user/userFind.html";
    }
    // Account

    @GetMapping(value = "/acc")
    public String listUsers(Principal principal, Model model) {
        if(principal==null){
            return "/user/registration.html";
        }
        User activeuser = this.userDetailService.findByUsername(principal.getName());
        LOGGER.info("Active User :'{}'", activeuser);
        LOGGER.info("Active User :'{}'", activeuser.getRoles());
        model.addAttribute("userAcc", activeuser);
        return "/user/account.html";
    }

    //View
    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Model model) {
        Optional<User> users = userDetailService.findById(id);
        LOGGER.info("View : '{}' ", users);
        users.ifPresent(value -> model.addAttribute("users", value));
        return "/user/viewUser.html";
    }



    //Pagination and Sorting
    @GetMapping(value = "/page/{pageNo}/size/{pageSize}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            @PathVariable(value = "pageSize") int pageSize,@RequestParam(value="sortField",required = false) String sortField,
			@RequestParam(value="sortDir",required = false) String sortDirection, Model model) {
        Page<User> page = userDetailService.listPaginated(pageNo, pageSize,sortField,sortDirection);
        List<User> listUsers = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDirection);
		model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "user/user.html";
    }

    @PostMapping(value = "/page/{pageNo}/size/{pageSize}")
    public String findPaginatedSize(@RequestParam(value = "pageSize") Integer pageSize, Model model) {
        if (pageSize == null) {
            pageSize = 5;
        }
        model.addAttribute("pageSize", pageSize);
      return findPaginated(1, pageSize,"username","asc", model);
    }
}
