package com.example.TestForRCDigital.services;

import com.example.TestForRCDigital.entity.Pet;
import com.example.TestForRCDigital.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById (int id) {
        return petRepository.findById(id);
    }

    @Transactional
    public void createPet (String name) {
        jdbcTemplate.update("INSERT into pet (\"name\") values (?);", name);
    }

    @Transactional
    public void updatePet (int id, String name) {
        jdbcTemplate.update("update pet set name = ? where id = ?;", name, id);
    }

    @Transactional
    public void deleteById(int id) {
        petRepository.deleteById(id);
    }

}
