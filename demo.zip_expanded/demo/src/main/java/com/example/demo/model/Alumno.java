package com.example.demo.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumno")

public class Alumno {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name= "nombre")
	    private String nombre;
	    @Column(name= "fechanacimiento")
	    private LocalDate fechaNacimiento;
	
	    public Alumno(Long id, String nombre, LocalDate fechaNacimiento) {
	    	this.id= id;
	    	this.nombre= nombre;
	    	this.fechaNacimiento= fechaNacimiento;
	    }

	    public Alumno() {
	   
	    }
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setNombre(String nombre) {
			this.nombre= nombre;
			
		}
		public LocalDate getFechaNacimiento() {
			// TODO Auto-generated method stub
			return fechaNacimiento;
		}
		public void setFechaNacimiento(LocalDate fechaNacimiento) {
			// TODO Auto-generated method stub
			this.fechaNacimiento=fechaNacimiento;
		}

		public String getNombre() {
			return nombre;
		}
		
}
