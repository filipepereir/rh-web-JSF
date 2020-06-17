package com.rh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Cargo;

@Named(value = "pesquisaCargoBean")
@ViewScoped
public class PesquisaCargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Cargo> cargos;
	private Cargo cargoSelecionado;

	@Inject
	private GenericDAO<Cargo> cargoDAO;

	public PesquisaCargoBean() {
		cargos = new ArrayList<Cargo>();
		cargoSelecionado = new Cargo();
	}

	@PostConstruct
	public void init() {
		cargos = cargoDAO.listarTodos(Cargo.class);
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public Cargo getCargoSelecionado() {
		return cargoSelecionado;
	}

	public void setCargoSelecionado(Cargo cargoSelecionado) {
		this.cargoSelecionado = cargoSelecionado;
	}

}
