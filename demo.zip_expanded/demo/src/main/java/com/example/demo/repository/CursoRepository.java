package com.example.demo.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Curso;
import com.example.demo.model.Docente;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	List<Curso> findByFechaFinLessThanEqual(LocalDate fechaActual);
	Optional<Curso> findByDocenteIdAndFechaFinGreaterThanEqual(Long docenteId, LocalDate fechaActual);
	
}