package com.taller.app.articulo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.taller.app.articulo.model.entity.Articulo;

public interface IArticuloDao extends CrudRepository<Articulo, Long> {

}
