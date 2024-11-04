package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tema")

public class Tema {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name= "nombre")
	    private String nombre;
	    @Column(name= "descripcion")
	    private String descripcion;
	
	    public Tema(Long id, String nombre, String descripcion ) {
	    	this.id= id;
	    	this.nombre= nombre;
	    	this.descripcion= descripcion;
	    }

	    public Tema() {
	   
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
		public String getDescripcion() {
			// TODO Auto-generated method stub
			return this.descripcion;
		}
		public void setDescripcion(String descripcion) {
			// TODO Auto-generated method stub
			this.descripcion=descripcion;
		}

		public String getNombre() {
			return nombre;
		}
		
}