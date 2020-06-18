package com.rh.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Contratacao;
import com.rh.modelo.Funcionario;
import com.rh.modelo.Status;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Funcionario> daoFuncionario;

	public void salvar(Funcionario funcionario) {
		daoFuncionario.salvar(funcionario);

	}

	public boolean verificaFuncionario(Long idFuncionario) {

		Funcionario funcionario = (Funcionario) daoFuncionario.buscarPorId(Funcionario.class, idFuncionario);

		if (funcionario != null) {
			return true;
		}

		return false;
	}

	public Funcionario pesquisaFuncionario(Long codigo) {
		return daoFuncionario.buscarPorId(Funcionario.class, codigo);

	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listaFiltrada(Status status) {

		List<Funcionario> lista = new ArrayList<Funcionario>();

		List<Object[]> resultado = daoFuncionario.getEntityManager().createQuery(
				"select f.codigo, f.nome, f.contratacao.status from Funcionario f where f.contratacao.status in (:status) group by f.nome")
				.setParameter("status", status).getResultList();

		for (Object[] res : resultado) {

			Funcionario funcionario = new Funcionario();

			Long codigo = (Long) res[0];
			String nome = (String) res[1];
			Status statusRecebido = (Status) res[2];

			funcionario.setCodigo(codigo);
			funcionario.setNome(nome);

			Contratacao contratacao = new Contratacao();

			contratacao.setStatus(statusRecebido);
			funcionario.setContratacao(contratacao);

			lista.add(funcionario);

		}

		return lista;
	}

}
