package com.rh.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Cargo;

public class CargoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Cargo> cargoDAO;

	public void salvar(Cargo cargo) {
		cargoDAO.salvar(cargo);

	}

	public List<Cargo> listarTodosCargos() {
		return cargoDAO.listarTodos(Cargo.class);
	}

}
