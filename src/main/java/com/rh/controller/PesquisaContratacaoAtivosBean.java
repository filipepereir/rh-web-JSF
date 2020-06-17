package com.rh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Contratacao;
import com.rh.modelo.Funcionario;
import com.rh.modelo.Status;

@Named(value = "pesquisaContratacaoAtivosBean")
@RequestScoped
@SuppressWarnings("unchecked")
public class PesquisaContratacaoAtivosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contratacao contratacao;

	private List<Funcionario> funcionarios;

	private List<Status> status;

	@SuppressWarnings("rawtypes")
	@Inject
	private GenericDAO dao;

	public PesquisaContratacaoAtivosBean() {
		funcionarios = new ArrayList<Funcionario>();
		contratacao = new Contratacao();
		status = new ArrayList<Status>();

	}

	@PostConstruct
	public void init() {
//		funcionarios = dao.getEntityManager().createQuery(
//				"select new Funcionario (f.codigo, f.nome, f.contratacao) from Funcionario f where f.contratacao.status in (:status)",
//				Funcionario.class).setParameter("status", Status.ATIVO).getResultList();

		status = Arrays.asList(Status.values());

	}

	public void listarFuncionario() {

		System.out.println("TO AQUI");

		System.out.println(contratacao.getStatus());

		listaFiltrada(contratacao.getStatus());

	}

	public List<Funcionario> listaFiltrada(Status statuss) {

		List<Object[]> resultado = dao.getEntityManager().createQuery(
				"select f.codigo, f.nome, f.contratacao.status from Funcionario f where f.contratacao.status in (:status) group by f.nome")
				.setParameter("status", statuss).getResultList();

		for (Object[] res : resultado) {

			Funcionario funcionario = new Funcionario();

			Long codigo = (Long) res[0];
			String nome = (String) res[1];
			Status status = (Status) res[2];

			funcionario.setCodigo(codigo);
			funcionario.setNome(nome);

			Contratacao contratacao = new Contratacao();

			contratacao.setStatus(status);
			funcionario.setContratacao(contratacao);

			funcionarios.add(funcionario);

		}

		return funcionarios;
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
