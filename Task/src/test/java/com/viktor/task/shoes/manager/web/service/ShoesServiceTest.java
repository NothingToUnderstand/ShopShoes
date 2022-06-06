package com.viktor.task.shoes.manager.web.service;

import com.viktor.task.shoes.manager.web.model.Shoes;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.easymock.*;
import org.junit.jupiter.api.*;

import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ShoesServiceTest extends EasyMockSupport {
  final static Logger log = LoggerFactory.getLogger(ShoesServiceTest.class);
  private static  ShoesService mockShoesService;
  private  List<Shoes>sh=new ArrayList<>();
  @Autowired
  Mockito mock;

  @BeforeAll
  public static void init() {
    mockShoesService=Mockito.mock(ShoesService.class);
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
     log.info("sh:{}",sh.size());
}
@AfterEach
public void clearList(){
  sh.clear();
}

    @Test
    void testFindAll()throws Exception {
      when(mockShoesService.findAll()).thenReturn(sh);
      log.info("Sh find all:{}",sh);
      List<Shoes> findAll=mockShoesService.findAll();
      assertTrue(findAll!=null);
      assertEquals(3, findAll.size());
      verify(mockShoesService,times(1)).findAll();
      assertEquals(findAll,sh);

     
    }

   
    @Test
    @DisplayName("Save Shoes is invoked")
    void testSaveShoes()throws Exception { 
      mockShoesService.saveShoes(sh.get(1));
verify(mockShoesService,times(1)).saveShoes(sh.get(1));
assertEquals(1,sh.get(0).getId());
    }
}
