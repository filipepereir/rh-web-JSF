package com.rh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.modelo.Empresa;
import com.rh.service.EmpresaService;

@Named(value = "pesquisaEmpresaBean")
@ViewScoped
public class PesquisaEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Empresa> empresas;

	private Empresa empresaSelecionada;

	@Inject
	private EmpresaService empresaService;

	public PesquisaEmpresaBean() {
		empresas = new ArrayList<Empresa>();
		empresaSelecionada = new Empresa();
	}

	@PostConstruct
	public void init() {
		empresas = empresaService.buscarTodasEmpresas();
	}

	public void excluir() {

	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

}
