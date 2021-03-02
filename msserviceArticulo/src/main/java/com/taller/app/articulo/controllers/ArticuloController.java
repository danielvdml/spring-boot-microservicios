package com.taller.app.articulo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taller.app.articulo.model.entity.Articulo;
import com.taller.app.articulo.model.service.ArticuloServiceImpl;
import com.taller.app.articulo.model.service.IArticuloService;

@RestController
public class ArticuloController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IArticuloService iArticuloService;

	@GetMapping("/listar")
	public List<Articulo> listar() {
		System.out.println("Probando metodo listar...");
		return iArticuloService.findAll().stream().map(articulo -> {
			articulo.setPort(port);
			return articulo;
		}).collect(Collectors.toList());
	}

	@GetMapping("/buscar/{id}")
	public Articulo detalle(@PathVariable Long id) throws Exception {
		Articulo articulo = iArticuloService.findById(id);
		articulo.setPort(port);
		
		return articulo;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo crear(@RequestBody Articulo articulo) {
		
		return iArticuloService.save(articulo);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo editar(@RequestBody Articulo articulo,@PathVariable Long id) {
		Articulo art= iArticuloService.findById(id);
		art.setNombre(articulo.getNombre());
		art.setPrecio(articulo.getPrecio());
		
		return iArticuloService.save(art);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		
		iArticuloService.deleteById(id);
		
	}
	
	
	
	
}
