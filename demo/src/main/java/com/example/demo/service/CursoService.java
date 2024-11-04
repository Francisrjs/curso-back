package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumno;
import com.example.demo.model.Curso;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.CursoRepository;

import jakarta.persistence.CascadeType;
import jakarta.transaction.Transactional;

@Service
public class CursoService {
private static final int List = 0;
private final CursoRepository cursoRepository;
private final AlumnoRepository alumnoRepository;
public CursoService (CursoRepository cursoRepository, AlumnoRepository alumnoRepository) {
	this.cursoRepository= cursoRepository;
	 this.alumnoRepository = alumnoRepository;
}
@Transactional

public Curso saveCurso(Curso curso) {
    try {
        // Validar que los alumnos no estén vacíos antes de persistir
        if (curso.getAlumnos() != null && !curso.getAlumnos().isEmpty()) {
            List<Alumno> alumnosPersistidos = curso.getAlumnos().stream().map(alumno -> {
                if (alumno.getId() != null) {
                    // Si el alumno tiene un ID, significa que ya está guardado en la base de datos
                    return alumnoRepository.findById(alumno.getId()).orElse(null);
                } else {
                    // Si no tiene ID, retornamos null para no agregarlo
                    return null;
                }
            }).filter(Objects::nonNull) // Filtramos los null para evitar agregar alumnos en blanco
            .collect(Collectors.toList());

            // Asignar la lista persistida de alumnos al curso
            curso.setAlumnos(alumnosPersistidos);
        } else {
            // Si no hay alumnos, asignamos una lista vacía
            curso.setAlumnos(new ArrayList<>());
        }

        // Guardar el curso
        return cursoRepository.save(curso);
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to save curso: " + e.getMessage());
    }
}// Get all cursos
public List<Curso> fetchAllCursos() {
    try {
        return cursoRepository.findAll();
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to fetch all cursos: " + e.getMessage());
    }
}
//Get a curso by ID
public Optional<Curso> fetchCursoById(Long id) {
     try {
         return cursoRepository.findById(id);
     } catch (Exception e) {
         // Handle exception or log the error
         throw new RuntimeException("Failed to fetch curso by ID: " + e.getMessage());
     }
 }
public Optional<Curso> updateCurso(Long id, Curso updatedCurso) {
    try {
        Optional<Curso> existingCursoOptional = cursoRepository.findById(id);
        if (existingCursoOptional.isPresent()) {
            Curso existingCurso = existingCursoOptional.get();
            existingCurso.setFechaInicio(updatedCurso.getFechaInicio());
            existingCurso.setFechaFin(updatedCurso.getFechaFin());
            existingCurso.setTema(updatedCurso.getTema());
            existingCurso.setDocente(updatedCurso.getDocente());
            existingCurso.setAlumnos(updatedCurso.getAlumnos());
            
            Curso savedEntity = cursoRepository.save(existingCurso);
            return Optional.of(savedEntity);
        } else {
            return Optional.empty();
        }
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to update curso: " + e.getMessage());
    }
}
public boolean deleteCurso(Long id) {
    try {
        cursoRepository.deleteById(id);
        return true; // Deletion successful
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to delete curso: " + e.getMessage());
    }
}
public List<Alumno> getAlumnosCursoVigentePorDocente(Long docenteId){
	   LocalDate fechaActual = LocalDate.now();
	  Optional<Curso> cursoVigente = cursoRepository.findByDocenteIdAndFechaFinGreaterThanEqual(docenteId, fechaActual);
	  if (cursoVigente.isPresent()) {
		  return cursoVigente.get().getAlumnos();  
	  }else {
		  return new ArrayList<>();//vacio
	  }
	  }


public List<Curso> getCursosVigentes(LocalDate fecha) {
    try {
    	 return cursoRepository.findByFechaFinLessThanEqual(fecha);
    } catch (Exception e) {
        // Handle exception or log the error
        throw new RuntimeException("Failed to fetch all cursos: " + e.getMessage());
    }
}
}