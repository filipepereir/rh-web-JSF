package com.rh.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.modelo.Empresa;
import com.rh.service.EmpresaService;

@Named(value = "cadastroEmpresaBean")
@ViewScoped
public class CadastroEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Empresa empresa;

	@Inject
	private EmpresaService empresaService;

	public CadastroEmpresaBean() {
		limpar();
	}

	public void salvar() {

		empresaService.salvar(empresa);
		limpar();

	}

	private void limpar() {
		empresa = new Empresa();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
