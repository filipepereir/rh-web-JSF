package com.rh.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Funcionario;

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

}
