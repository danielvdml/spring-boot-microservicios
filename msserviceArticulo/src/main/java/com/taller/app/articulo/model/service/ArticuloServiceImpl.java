package com.taller.app.articulo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.app.articulo.model.dao.IArticuloDao;
import com.taller.app.articulo.model.entity.Articulo;

@Service
public class ArticuloServiceImpl implements IArticuloService {

	@Autowired
	private IArticuloDao iArticuloDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {
		// TODO Auto-generated method stub
		return (List<Articulo>)iArticuloDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) {
		// TODO Auto-generated method stub
		return iArticuloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Articulo save(Articulo articulo) {
		// TODO Auto-generated method stub
		return iArticuloDao.save(articulo);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		iArticuloDao.deleteById(id);
	}

}
