package com.viktor.task.shoes.manager.web.controllers;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collection;

import com.viktor.task.shoes.manager.web.dao.UserDao;
import com.viktor.task.shoes.manager.web.model.Role;
import com.viktor.task.shoes.manager.web.model.User;
import com.viktor.task.shoes.manager.web.service.UserDetailService;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.Matchers.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDetailService userDetailService;
    @MockBean
    private UserDao userDao;

   public static ArrayList<User> users() {
      
    Collection<Role> roles=new HashSet<Role>();
    roles.add(new Role(2L, "ROLE_USER"));
    User user1 = new User(1,"Mick","111" , "mick@gamil.com",roles);
    User user2 = new User(2,"Bob","111","bob@gmail.com",roles);
ArrayList<User> usersList=new ArrayList<>();
usersList.add(user1);
usersList.add(user2);
       return usersList;
   }
    @Test
    @DisplayName("GET /rest/users/page/1/size/5")
    void testGetUserList()throws Exception{
        Collection<Role> roles=new HashSet<Role>();
        roles.add(new Role(2L, "ROLE_USER"));
       
        User user1 = new User(1,"Mick","111" , "mick@gamil.com",roles);
        User user2 = new User(2,"Bob","111","bob@gmail.com",roles);
        userDao.save(user1);
        userDao.save(user2);
      
        
        doReturn(List.of(user1,user2)).when( userDetailService).listPaginated(1, 2,"id", "asc");
       
        mockMvc.perform(get("/rest/users/"))
        // Validate the response code and content type
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].username", is("Mick")))
        .andExpect(jsonPath("$[0].password", is("111")))
        .andExpect(jsonPath("$[0].email", is("mick@gamil.com")))
        .andExpect(jsonPath("$[0].roles", is("ROLE_USER")))

        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].username", is("Bob")))
        .andExpect(jsonPath("$[1].password", is("111")))
        .andExpect(jsonPath("$[1].email", is("bob@gamil.com")))
        .andExpect(jsonPath("$[1].roles", is("ROLE_USER")));

    }
    @Test
    @DisplayName("GET /rest/users/1")
    void testGetUserById() throws Exception {
        Collection<Role> roles=new HashSet<Role>();
        roles.add(new Role(2L, "ROLE_USER"));
        User user1 = new User(1,"Mick","111" , "mick@gamil.com",roles);
        doReturn(Optional.of(user1)).when( userDetailService).findById(1L);

        // Execute the GET request
        mockMvc.perform(get("/rest/users/{id}", 1L))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$.id").value("1"))
                .andExpect( jsonPath("$.username").value("Mick"))
                .andExpect(jsonPath("$.password").value("111"))
                .andExpect(jsonPath("$.email").value("mick@gamil.com"))
                .andExpect( jsonPath("$.roles.[0].authority").value("ROLE_USER"));
                       
    }
    @Test
    @DisplayName("GET /rest/users")
    void testGetUserByUsername() throws Exception {
        users();
    }
 
}
