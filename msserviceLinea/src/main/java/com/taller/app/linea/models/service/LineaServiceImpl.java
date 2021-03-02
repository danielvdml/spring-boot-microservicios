package com.taller.app.linea.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.taller.app.linea.models.Articulo;
import com.taller.app.linea.models.Linea;

@Service("serviceRestTemplate")
public class LineaServiceImpl implements LineaService {

	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Linea> findAll() {

		List<Articulo> articulos = Arrays
				.asList(clienteRest.getForObject("http://service-articulos/listar", Articulo[].class));
		return articulos.stream().map(a -> new Linea(a, 1)).collect(Collectors.toList());
	}

	@Override
	public Linea findByid(Long id, Integer cantidad) {

		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		Articulo articulo = clienteRest.getForObject("http://service-articulos/buscar/{id}", Articulo.class,
				pathVariables);
		return new Linea(articulo, cantidad);
	}

	@Override
	public Articulo save(Articulo articulo) {

		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Articulo> body = new HttpEntity<Articulo>(articulo);

		ResponseEntity<Articulo> response = clienteRest.exchange("http://service-articulos/crear", HttpMethod.POST,
				body, Articulo.class);
		Articulo articuloResponse = response.getBody();
		return articuloResponse;
	}

	@Override
	public Articulo update(Articulo articulo, Long id) {
		
				
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		HttpEntity<Articulo> body = new HttpEntity<Articulo>(articulo);
		ResponseEntity<Articulo> response = clienteRest.exchange("http://service-articulos/editar/{id}",
				HttpMethod.PUT, body, Articulo.class, pathVariables);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clienteRest.delete("http://service-articulos/eliminar/{id}", pathVariables);

	}

}
