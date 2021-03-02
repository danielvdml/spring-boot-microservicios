package com.taller.app.articulo.model.service;

import java.util.List;

import com.taller.app.articulo.model.entity.Articulo;

public interface IArticuloService {
  public List<Articulo>  findAll();
  public Articulo findById(Long id);
  
  public Articulo save(Articulo articulo);
  
  public void deleteById(Long id)
;}
