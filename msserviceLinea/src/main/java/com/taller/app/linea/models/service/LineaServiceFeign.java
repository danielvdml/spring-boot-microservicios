
package com.taller.app.linea.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.taller.app.linea.clientes.ArticuloClienteRest;
import com.taller.app.linea.models.Articulo;
import com.taller.app.linea.models.Linea;

@Service("serviceFeign")
//@Primary
public class LineaServiceFeign implements LineaService {

	@Autowired
	private ArticuloClienteRest clienteFeign;

	@Override
	public List<Linea> findAll() {
		return clienteFeign.listar().stream().map(a -> new Linea(a, 1)).collect(Collectors.toList());
	}

	@Override
	public Linea findByid(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
		return new Linea(clienteFeign.detalle(id), cantidad);
	}

	@Override
	public Articulo save(Articulo articulo) {
		// TODO Auto-generated method stub
		return clienteFeign.crear(articulo);
	}

	@Override
	public Articulo update(Articulo articulo, Long id) {
		// TODO Auto-generated method stub
		return clienteFeign.update(articulo, id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteFeign.eliminar(id);
	}

}
