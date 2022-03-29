package com.bootcampjava.starwars.service;

import com.bootcampjava.starwars.model.Jedi;
import com.bootcampjava.starwars.repository.JediRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JediTestService {

    @Autowired
    private JediService jediService;

    @MockBean
    private JediRepositoryImpl jediRepository;

    @DisplayName("Should return jedi with sucess")
    @Test
    public void testFindByIdSuccess() {

        Jedi mockJedi = new Jedi(1, "Jedi Name", 10, 1);
        doReturn(Optional.of(mockJedi)).when(jediRepository).findById(1);

        Optional<Jedi> returnedJedi  = jediService.findById(1);

        Assertions.assertTrue(returnedJedi.isPresent(), "Jedi was not found");
        Assertions.assertSame(returnedJedi.get(), mockJedi, "Jedis must be the same");
    }

    @DisplayName("Should not return jedi")
    @Test
    public void testFindByIdNotFound(){
        doReturn(Optional.empty()).when(jediRepository).findById(20);

        Optional<Jedi> returnedJedi  = jediService.findById(20);

        Assertions.assertFalse(returnedJedi.isPresent(), "Jedi was not found");
        // was not found
    }


    @Test
    @DisplayName("Should return All Jedis with success")
    void testFindAllSucess() {

        Jedi mockJedi = new Jedi(1, "Jedi 1", 10, 1);
        Jedi mockJedi2 = new Jedi(2, "Jedi 2", 15, 3);
        doReturn(Arrays.asList(mockJedi, mockJedi2)).when(jediRepository).findAll();

        // Execute the service call
        List<Jedi> jedis = jediService.findAll();

        // Assertions.assertFalse(returnedJedi.isEmpty(), "No Jedi was found");
        //Assertions.assertSame(returnedJedi.get(0), mockJedi, "Jedis must be the same");
        Assertions.assertEquals(2, jedis.size(), "Jedis de mesma forca encontrados");
    }




    @Test
    @DisplayName("Should return empty list of result when Jedis are not found")
    public void testFindAllNotFound() {

        // cenario
        doReturn(List.of()).when(jediRepository).findAll();

        // execucao
        List<Jedi> returnedJedi = jediService.findAll();

        // assert
        Assertions.assertTrue(returnedJedi.isEmpty(), "No Jedi was found");
    }
}
