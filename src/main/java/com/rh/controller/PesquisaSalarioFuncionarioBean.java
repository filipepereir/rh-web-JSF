package com.rh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.modelo.Funcionario;
import com.rh.service.FuncionariosService;

@Named(value = "pesquisaSalarioFuncionarioBean")
@ViewScoped
public class PesquisaSalarioFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Funcionario> funcionarios;

	@Inject
	private FuncionariosService funcionarioService;

	public PesquisaSalarioFuncionarioBean() {
		this.funcionarios = new ArrayList<Funcionario>();
	}

	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.pesquisaNomeESalario();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

}
