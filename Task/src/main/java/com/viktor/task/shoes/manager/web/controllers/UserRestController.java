package com.viktor.task.shoes.manager.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import com.viktor.task.shoes.manager.web.dto.ResponseUser;
import com.viktor.task.shoes.manager.web.model.User;
import com.viktor.task.shoes.manager.web.service.UserDetailService;

@CrossOrigin(origins = "http://localhost:4200/**", maxAge = 3600)
@RestController
@RequestMapping("/rest/users")
public class UserRestController {
    private static final Logger LOG = LoggerFactory.getLogger(ShoesRestController.class);

    private UserDetailService userDetailService;
  
    @Autowired
    public UserRestController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

   

    // list+serch
    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getUserList(@RequestParam(value = "username", required = false) String username) {
        LOG.info("User username: '{}'", username);
        if (username == null || username == "") {
            return findPaginated(1, 5, "id", "asc");
        } else {
            User user = this.userDetailService.findByUsername(username);
            ArrayList<User> users = new ArrayList<>();
            users.add(user);
            LOG.info("Users search: '{}'", user);
            return ResponseEntity.ok(users);
        }

    }

    // regist
    @PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> registUser(@RequestBody User user) {
        try {
            this.userDetailService.saveUser(user);
            LOG.info(" new user: '{}'", user);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
        }
    }

    // delete
    @RequestMapping(name = "delete", value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        Object deletedUser;
        if (null != id) {
            deletedUser= this.userDetailService.findById( id);
            LOG.info(" deleted user: '{}'", deletedUser);
            this.userDetailService.deleteUser(id);
            LOG.info("Deleted successfully");
            return  ResponseEntity.status(HttpStatus.OK).body(deletedUser);

        }
       
        return ResponseEntity.ok("Cannot delete user with null id");
    }

    // edit
    @RequestMapping(name = "edit", value = "{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
    public ResponseEntity<?> editUser(@PathVariable("id") Long id, @RequestBody User user) {

        if (null != id) {
            Optional<User> userId = this.userDetailService.findById(id);
            User users = userId.get();
            user.setPassword(users.getPassword());
            user.setId(users.getId());

            this.userDetailService.saveUser(user);
            LOG.info("edited user: '{}'", user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok("Cannot edit user with null id");
        }
    }

    // acc
    @GetMapping(value = "/acc", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getAcc(Principal principal) {
        User activeuser = this.userDetailService.findByUsername(principal.getName());

        LOG.info("User auth: '{}'", activeuser);
        return ResponseEntity.ok(activeuser);
    }
    //Info
@GetMapping(name="info",value = "{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
public ResponseEntity<?> getUserInfo(@PathVariable("id") Long id){
    if (null != id) {
        Optional<User> userId = this.userDetailService.findById(id);
        LOG.info("Found user:",userId);
        return  ResponseEntity.ok(userId);
        
    }else{
        return ResponseEntity.ok("Cannot find user with null id");
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
Page<User> page = userDetailService.listPaginated(pageNo, pageSize,sortField,sortDirection);
List<User> listUsers = page.getContent();  
return  ResponseEntity.ok(new ResponseUser(listUsers,page.getTotalPages(),page.getTotalElements()));
}


}
