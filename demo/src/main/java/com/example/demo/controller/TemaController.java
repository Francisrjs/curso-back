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

import com.example.demo.model.Tema;
import com.example.demo.service.TemaService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TemaController {
private final TemaService temaService;
public TemaController(TemaService temaService) {
	this.temaService= temaService;
}
// Create a new tema
@PostMapping(value = "/tema")
public ResponseEntity<Tema> saveTema(@RequestBody Tema tema) {
    // Verifica el valor del nombre antes de guardar
    System.out.println("Nombre recibido: " + tema.getNombre());
    Tema savedTema = temaService.saveTema(tema);
    return ResponseEntity.ok(savedTema);
}
// Get all temas
@GetMapping("/temas")
public ResponseEntity<List<Tema>> getAllTemas() {
    List<Tema> temas = temaService.fetchAllTemas();
    return ResponseEntity.ok(temas);
}


// Get a tema by ID
@GetMapping("/tema/{id}")
public ResponseEntity<Tema> getTemaById(@PathVariable Long id) {
    Optional<Tema> temaOptional = temaService.fetchTemaById(id);
    return temaOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
@PutMapping(path = "/tema/{TemaId}")
public ResponseEntity<Tema> updateTema(@PathVariable Long TemaId, @RequestBody Tema tema) {
    Optional<Tema> updatedTemaOptional = temaService.updateTema(TemaId, tema);
    return updatedTemaOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
@DeleteMapping("/tema/{id}")
public ResponseEntity<Void> deleteTema(@PathVariable Long id) {
    boolean deleted = temaService.deleteTema(id);
    if (deleted) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
}

