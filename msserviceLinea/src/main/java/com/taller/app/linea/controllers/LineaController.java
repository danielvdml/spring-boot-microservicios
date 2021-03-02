package com.taller.app.linea.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taller.app.linea.models.Articulo;
import com.taller.app.linea.models.Linea;
import com.taller.app.linea.models.service.LineaService;

@RefreshScope
@RestController
public class LineaController {
	private static Logger log = LoggerFactory.getLogger(LineaController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	//@Qualifier("serviceRestTemplate")
	private LineaService lineaService;

	
	@Value("${configuracion.texto}")
	private String texto;

	@GetMapping("/listar")
	public List<Linea> listar() {
		System.out.println("LineaController: listar");
		return lineaService.findAll();
	}

	@HystrixCommand(fallbackMethod = "getDatos")
	@GetMapping("/buscar/{id}/cantidad/{cantidad}")
	public Linea detalle(@PathVariable Long id, @PathVariable Integer cantidad) {

		return lineaService.findByid(id, cantidad);
	}

	public Linea getDatos(Long id, Integer cantidad) {
		Linea linea = new Linea();
		Articulo articulo = new Articulo();
		linea.setCantidad(cantidad);
		articulo.setId(id);
		articulo.setNombre("Laptop HP full HD Gamer..");
		articulo.setPrecio(2000.30);
		linea.setArticulo(articulo);
		return linea;

	}

	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {

		log.info(texto);
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);

		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("desarrollo")) {

			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));

		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo crear(@RequestBody Articulo articulo) {
		return lineaService.save(articulo);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo editar(@RequestBody Articulo articulo, @PathVariable Long id) {
		return lineaService.update(articulo, id);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		lineaService.delete(id);
	}
}
