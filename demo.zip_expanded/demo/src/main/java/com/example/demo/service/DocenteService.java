package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Docente;
import com.example.demo.repository.DocenteRepository;

@Service
public class DocenteService {
private final DocenteRepository docenteRepository;
public DocenteService (DocenteRepository docenteRepository) {
	this.docenteRepository= docenteRepository;
}
public Docente saveDocente(Docente docente) {
    try {
        return docenteRepository.save(docente);
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to save docente: " + e.getMessage());
    }
}// Get all docentes
public List<Docente> fetchAllDocentes() {
    try {
        return docenteRepository.findAll();
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to fetch all docentes: " + e.getMessage());
    }
}
//Get a docente by ID
public Optional<Docente> fetchDocenteById(Long id) {
     try {
         return docenteRepository.findById(id);
     } catch (Exception e) {
         // Handle exception or log the error
         throw new RuntimeException("Failed to fetch docente by ID: " + e.getMessage());
     }
 }
public Optional<Docente> updateDocente(Long id, Docente updatedDocente) {
    try {
        Optional<Docente> existingDocenteOptional = docenteRepository.findById(id);
        if (existingDocenteOptional.isPresent()) {
            Docente existingDocente = existingDocenteOptional.get();
            existingDocente.setNombre(updatedDocente.getNombre());
            existingDocente.setLegajo(updatedDocente.getLegajo());
            Docente savedEntity = docenteRepository.save(existingDocente);
            return Optional.of(savedEntity);
        } else {
            return Optional.empty();
        }
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to update docente: " + e.getMessage());
    }
}
public boolean deleteDocente(Long id) {
    try {
        docenteRepository.deleteById(id);
        return true; // Deletion successful
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to delete docente: " + e.getMessage());
    }
}
}
