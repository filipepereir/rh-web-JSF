package com.rh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Cargo;
import com.rh.modelo.Contratacao;
import com.rh.modelo.Empresa;
import com.rh.modelo.Endereco;
import com.rh.modelo.EstadoCivil;
import com.rh.modelo.Funcionario;
import com.rh.modelo.Salario;
import com.rh.modelo.Status;
import com.rh.modelo.Telefone;
import com.rh.modelo.Transporte;
import com.rh.service.FuncionarioService;

@Named(value = "cadastroFuncionarioBean")
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	private List<Status> status;

	private List<Cargo> cargos;
	private List<Empresa> empresas;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private GenericDAO<Cargo> daoCargo;

	@Inject
	private GenericDAO<Empresa> daoEmpresa;

	private List<EstadoCivil> civils;
	private List<Transporte> transportes;

	public CadastroFuncionarioBean() {

		limpar();
	}

	public void limpar() {

		this.funcionario = new Funcionario();

		this.funcionario.setTelefone(new Telefone());
		this.funcionario.setSalario(new Salario());
		this.funcionario.setEndereco(new Endereco());
		this.funcionario.setContratacao(new Contratacao());
		this.civils = new ArrayList<EstadoCivil>();
		this.transportes = new ArrayList<Transporte>();
		this.status = new ArrayList<Status>();
		this.cargos = new ArrayList<Cargo>();
		this.empresas = new ArrayList<Empresa>();

	}

	@PostConstruct
	public void init() {

		civils = Arrays.asList(EstadoCivil.values());
		transportes = Arrays.asList(Transporte.values());
		cargos = daoCargo.listarTodos(Cargo.class);
		empresas = daoEmpresa.listarTodos(Empresa.class);
		status = Arrays.asList(Status.values());

	}

	public void salvar() {

		funcionarioService.salvar(funcionario);
		limpar();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {

		this.funcionario = funcionario;
	}

	public List<EstadoCivil> getCivils() {
		return civils;
	}

	public void setCivils(List<EstadoCivil> civils) {
		this.civils = civils;
	}

	public List<Transporte> getTransportes() {
		return transportes;
	}

	public void setTransportes(List<Transporte> transportes) {
		this.transportes = transportes;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public List<Status> getStatus() {
		return status;
	}

}
