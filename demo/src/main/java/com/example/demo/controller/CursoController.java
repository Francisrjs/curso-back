package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Asegúrate de incluir todas las anotaciones

import com.example.demo.model.Alumno;
import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Guardar un nuevo curso
    @PostMapping(value = "/curso")
    public ResponseEntity<Curso> saveCurso(@RequestBody Curso curso) {
        try {
            Curso savedCurso = cursoService.saveCurso(curso);
            return ResponseEntity.ok(savedCurso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Obtener todos los cursos
    @GetMapping("/cursos")
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = cursoService.fetchAllCursos();
        return ResponseEntity.ok(cursos);
    }

    // Obtener un curso por ID
    @GetMapping("/curso/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoService.fetchCursoById(id);
        return cursoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un curso
    @PutMapping(path = "/curso/{CursoId}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long CursoId, @RequestBody Curso curso) {
        Optional<Curso> updatedCursoOptional = cursoService.updateCurso(CursoId, curso);
        return updatedCursoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un curso por ID
    @DeleteMapping("/curso/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        boolean deleted = cursoService.deleteCurso(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Obtener alumnos de un curso vigente por ID de docente
    @GetMapping("/docente/{id}/cursoVigente")
    public ResponseEntity<List<Alumno>> getAlumnosCursoVigentePorDocente(@PathVariable Long id) {
        List<Alumno> alumnosVigentes = cursoService.getAlumnosCursoVigentePorDocente(id);
        return ResponseEntity.ok(alumnosVigentes);
    }

    // Obtener cursos vigentes antes o en una fecha específica
    @GetMapping("/curso/cursosVigentes/{fecha}")
    public ResponseEntity<List<Curso>> findByFechaFinLessThanEqual(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
        List<Curso> cursosVigentes = cursoService.getCursosVigentes(fecha);
        return ResponseEntity.ok(cursosVigentes);
    }
}
