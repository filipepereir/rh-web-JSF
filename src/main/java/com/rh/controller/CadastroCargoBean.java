package com.rh.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.modelo.Cargo;
import com.rh.service.CargoService;

@Named(value = "cadastroCargoBean")
@ViewScoped
public class CadastroCargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cargo cargo;

	@Inject
	private CargoService cargoService;

	public CadastroCargoBean() {
		limpar();
	}

	private void limpar() {
		cargo = new Cargo();
	}

	public void salvar() {
		cargoService.salvar(cargo);
		limpar();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}
