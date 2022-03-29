package com.bootcampjava.starwars.service;

import com.bootcampjava.starwars.model.Jedi;
import com.bootcampjava.starwars.repository.JediRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    private static final Logger logger = LogManager.getLogger(JediService.class);

    private final JediRepositoryImpl jediRepositoryImpl;


    public JediService(JediRepositoryImpl jediRepositoryImpl) {
        this.jediRepositoryImpl = jediRepositoryImpl;
    }

    public Optional<Jedi> findById(Integer id) {
        logger.info("Find Jedi with id: {}", id);
        return jediRepositoryImpl.findById(id);
    }

    public List<Jedi> findAll() {
        logger.info("Find all Jedis on Galaxy");
        return jediRepositoryImpl.findAll();
    }

    public Jedi save(Jedi jedi) {
        jedi.setVersion(1);

        logger.info("Save Jedi to the database: {}", jedi);
        return jediRepositoryImpl.save(jedi);
    }

    public boolean update(Jedi jedi) {
        boolean updated = false;

        logger.info("Update jedi to the database: {}", jedi);

        Jedi savedProduct = this.save(jedi);

        if(savedProduct != null) updated = true;

        return updated;
    }

    public boolean delete(Integer id) {
        return true;
    }
}
