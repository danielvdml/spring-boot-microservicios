package com.taller.app.linea.models.service;

import java.util.List;

import com.taller.app.linea.models.Articulo;
import com.taller.app.linea.models.Linea;

public interface LineaService {
	
	public List<Linea> findAll();
	public Linea findByid(Long id, Integer cantidad);

	public Articulo save(Articulo articulo);
	
	public Articulo update(Articulo articulo, Long id);
	
	public void delete(Long id);

}
