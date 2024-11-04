package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Alumno;
import com.example.demo.repository.AlumnoRepository;

@Service
public class AlumnoService {
private final AlumnoRepository alumnoRepository;
public AlumnoService (AlumnoRepository alumnoRepository) {
	this.alumnoRepository= alumnoRepository;
}
public Alumno saveAlumno(Alumno alumno) {
    try {
        return alumnoRepository.save(alumno);
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to save alumno: " + e.getMessage());
    }
}// Get all alumnos
public List<Alumno> fetchAllAlumnos() {
    try {
        return alumnoRepository.findAll();
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to fetch all alumnos: " + e.getMessage());
    }
}
//Get a alumno by ID
public Optional<Alumno> fetchAlumnoById(Long id) {
     try {
         return alumnoRepository.findById(id);
     } catch (Exception e) {
         // Handle exception or log the error
         throw new RuntimeException("Failed to fetch alumno by ID: " + e.getMessage());
     }
 }
public Optional<Alumno> updateAlumno(Long id, Alumno updatedAlumno) {
    try {
        Optional<Alumno> existingAlumnoOptional = alumnoRepository.findById(id);
        if (existingAlumnoOptional.isPresent()) {
            Alumno existingAlumno = existingAlumnoOptional.get();
            existingAlumno.setNombre(updatedAlumno.getNombre());
            existingAlumno.setFechaNacimiento(updatedAlumno.getFechaNacimiento());
            Alumno savedEntity = alumnoRepository.save(existingAlumno);
            return Optional.of(savedEntity);
        } else {
            return Optional.empty();
        }
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to update alumno: " + e.getMessage());
    }
}
public boolean deleteAlumno(Long id) {
    try {
        alumnoRepository.deleteById(id);
        return true; // Deletion successful
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to delete alumno: " + e.getMessage());
    }
}
}
