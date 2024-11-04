package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Alumno;
//import com.example.demo.repository.AlumnoRepository;
import com.example.demo.service.AlumnoService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AlumnoController {
private final AlumnoService alumnoService;
public AlumnoController(AlumnoService alumnoService) {
	this.alumnoService= alumnoService;
}
// Create a new alumno
@PostMapping(value = "/alumno")

public ResponseEntity<Alumno> saveAlumno(@RequestBody Alumno alumno) {
    // Verifica el valor del nombre antes de guardar
    System.out.println("Nombre recibido: " + alumno.getNombre());
    Alumno savedAlumno = alumnoService.saveAlumno(alumno);
    return ResponseEntity.ok(savedAlumno);
}
// Get all alumnos
@GetMapping("/alumnos")
public ResponseEntity<List<Alumno>> getAllAlumnos() {
    List<Alumno> alumnos = alumnoService.fetchAllAlumnos();
    return ResponseEntity.ok(alumnos);
}


// Get a alumno by ID
@GetMapping("/alumno/{id}")
public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id) {
    Optional<Alumno> alumnoOptional = alumnoService.fetchAlumnoById(id);
    return alumnoOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
@PutMapping(path = "/alumno/{AlumnoId}")
public ResponseEntity<Alumno> updateAlumno(@PathVariable Long AlumnoId, @RequestBody Alumno alumno) {
    Optional<Alumno> updatedAlumnoOptional = alumnoService.updateAlumno(AlumnoId, alumno);
    return updatedAlumnoOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
@DeleteMapping("/alumno/{id}")
public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
    boolean deleted = alumnoService.deleteAlumno(id);
    if (deleted) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
}
