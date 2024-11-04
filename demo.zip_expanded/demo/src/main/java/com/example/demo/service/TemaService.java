package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Tema;
import com.example.demo.repository.TemaRepository;

@Service
public class TemaService {
private final TemaRepository temaRepository;
public TemaService (TemaRepository temaRepository) {
	this.temaRepository= temaRepository;
}
public Tema saveTema(Tema tema) {
    try {
        return temaRepository.save(tema);
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to save tema: " + e.getMessage());
    }
}// Get all temas
public List<Tema> fetchAllTemas() {
    try {
        return temaRepository.findAll();
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to fetch all temas: " + e.getMessage());
    }
}
//Get a tema by ID
public Optional<Tema> fetchTemaById(Long id) {
     try {
         return temaRepository.findById(id);
     } catch (Exception e) {
         // Handle exception or log the error
         throw new RuntimeException("Failed to fetch tema by ID: " + e.getMessage());
     }
 }
public Optional<Tema> updateTema(Long id, Tema updatedTema) {
    try {
        Optional<Tema> existingTemaOptional = temaRepository.findById(id);
        if (existingTemaOptional.isPresent()) {
            Tema existingTema = existingTemaOptional.get();
            existingTema.setNombre(updatedTema.getNombre());
            existingTema.setDescripcion(updatedTema.getDescripcion());
            Tema savedEntity = temaRepository.save(existingTema);
            return Optional.of(savedEntity);
        } else {
            return Optional.empty();
        }
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to update tema: " + e.getMessage());
    }
}
public boolean deleteTema(Long id) {
    try {
        temaRepository.deleteById(id);
        return true; // Deletion successful
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to delete tema: " + e.getMessage());
    }
}
}

