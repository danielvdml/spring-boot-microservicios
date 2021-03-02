package com.taller.app.linea.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.taller.app.linea.models.Articulo;
import com.taller.app.linea.models.Linea;

@FeignClient(name="service-articulos")
public interface ArticuloClienteRest {
	
	@GetMapping("/listar")
	public List<Articulo> listar();
		

	@GetMapping("/buscar/{id}")
	public Articulo detalle(@PathVariable Long id);
	
	@PostMapping("/crear")
	public Articulo crear(@RequestBody Articulo articulo);
	
	@PutMapping("/editar/{id}")
	public Articulo update(@RequestBody Articulo articulo, @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
	
}
