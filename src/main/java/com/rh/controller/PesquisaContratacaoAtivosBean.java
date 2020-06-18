package com.rh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.modelo.Contratacao;
import com.rh.modelo.Funcionario;
import com.rh.modelo.Status;
import com.rh.service.FuncionarioService;

@Named(value = "pesquisaContratacaoAtivosBean")
@RequestScoped
public class PesquisaContratacaoAtivosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contratacao contratacao;

	private List<Funcionario> funcionarios;

	private List<Status> status;

	@Inject
	private FuncionarioService funcionarioService;

	public PesquisaContratacaoAtivosBean() {
		funcionarios = new ArrayList<Funcionario>();
		contratacao = new Contratacao();
		status = new ArrayList<Status>();
	}

	@PostConstruct
	public void init() {

		status = Arrays.asList(Status.values());
	}

	public void listarFuncionario() {

		funcionarios = funcionarioService.listaFiltrada(contratacao.getStatus());

	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public Contratacao getContratacao() {
		return contratacao;
	}

	public List<Status> getStatus() {
		return status;
	}

}
