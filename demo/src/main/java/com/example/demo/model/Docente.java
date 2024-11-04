package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "docente")

public class Docente {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name= "nombre")
	    private String nombre;
	    @Column(name= "legajo")
	    private Long legajo;
	
		public Docente(Long id, String nombre, Long legajo) {
			this.id=id;
			this.nombre=nombre;
			this.legajo=legajo;
		}

	    public Docente() {
	   
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
		public Long getLegajo() {
			// TODO Auto-generated method stub
			return legajo;
		}
		public void setLegajo(Long legajo) {
			// TODO Auto-generated method stub
			this.legajo= legajo;
		}

		public String getNombre() {
			return nombre;
		}
		
}