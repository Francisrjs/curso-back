package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.Docente;
import com.example.demo.service.DocenteService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class DocenteController {
private final DocenteService docenteService;
public DocenteController(DocenteService docenteService) {
	this.docenteService= docenteService;
}
// Create a new docente
@PostMapping(value = "/docente")
public ResponseEntity<Docente> saveDocente(@RequestBody Docente docente) {
    // Verifica el valor del nombre antes de guardar
    System.out.println("Nombre recibido: " + docente.getNombre());
    Docente savedDocente = docenteService.saveDocente(docente);
    return ResponseEntity.ok(savedDocente);
}
// Get all docentes
@GetMapping("/docentes")
public ResponseEntity<List<Docente>> getAllDocentes() {
    List<Docente> docentes = docenteService.fetchAllDocentes();
    return ResponseEntity.ok(docentes);
}


// Get a docente by ID
@GetMapping("/docente/{id}")
public ResponseEntity<Docente> getDocenteById(@PathVariable Long id) {
    Optional<Docente> docenteOptional = docenteService.fetchDocenteById(id);
    return docenteOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
@PutMapping(path = "/docente/{DocenteId}")
public ResponseEntity<Docente> updateDocente(@PathVariable Long DocenteId, @RequestBody Docente docente) {
    Optional<Docente> updatedDocenteOptional = docenteService.updateDocente(DocenteId, docente);
    return updatedDocenteOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
@DeleteMapping("/docente/{id}")
public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
    boolean deleted = docenteService.deleteDocente(id);
    if (deleted) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
}
