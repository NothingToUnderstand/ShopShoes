package com.viktor.task.shoes.manager.web.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.x.Ok;
import com.viktor.task.shoes.manager.web.model.Shoes;
import com.viktor.task.shoes.manager.web.service.ShoesService;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;






@SpringBootTest
@AutoConfigureMockMvc
public class ShoesRestControllerTest {
    @InjectMocks
   ShoesRestController mockshoesRestController = new ShoesRestController(mockShoesService);

    @Autowired
    ObjectMapper objectMapper;
    
    
     @Autowired
    private static MockMvc mockMvc;

    final static Logger log = LoggerFactory.getLogger(ShoesRestControllerTest.class);
    private static  ShoesService mockShoesService;
    private  List<Shoes>sh=new ArrayList<>();
   
    @BeforeAll
    public static void init() {
      mockShoesService=Mockito.mock(ShoesService.class);
      
     ShoesRestController shoescontroller = new ShoesRestController(mockShoesService);
      mockMvc = MockMvcBuilders.standaloneSetup(shoescontroller).build();
  }
  @BeforeEach
  public void fillList(){
    log.info("Before Each");
    
    Shoes shoes1=new Shoes(1,"Bots", 2f, "black", 2);
    Shoes shoes2=new Shoes(2,"Adidas", 5f, "pink", 5);
    Shoes shoes3=new Shoes(3,"Nike", 3f, "green", 3);
        sh.add(shoes1);
        sh.add(shoes2);
        sh.add(shoes3);
  }
  @AfterEach
  public void clearList(){
    sh.clear();
  }
  @Test
  void testCreateShoes1() throws Exception{
      Shoes shoes=sh.get(0);

       mockMvc.perform(MockMvcRequestBuilders
        .post("http://localhost:8080/rest/shoes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(shoes))
        )
        
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
  }
    @Test
    void testCreateShoes() throws Exception{
        Shoes shoes=sh.get(0);
mockshoesRestController.createShoes(shoes);
        assertEquals(shoes,sh.get(0));
        verify(mockShoesService,times(1)).saveShoes(shoes);
        
    }
    @Test
    void testGetShoesInfo()throws Exception{
        Optional<Shoes> shoes=Optional.of(sh.get(1));
        when(mockShoesService.findById(1)).thenReturn(shoes);
        mockshoesRestController.getShoesInfo(1);
        verify(mockShoesService,times(1)).findById(1);
    }
    @Test
    void testEditShoes()throws Exception{
        Shoes shoes=new Shoes(1,"Nike", 2f, "red", 2);
        Optional<Shoes> shoesopti=Optional.of(sh.get(1));
        when(mockShoesService.findById(1)).thenReturn(shoesopti);
        mockshoesRestController.editShoes(1, shoes);
        verify(mockShoesService).findById(1);
        Shoes shoesFound=shoesopti.get();
        log.info("ShoesOpti {}",shoesFound);
        verify(mockShoesService).saveShoes(shoesFound);

    }
}
