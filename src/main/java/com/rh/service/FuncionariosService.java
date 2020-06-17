package com.rh.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Funcionario;
import com.rh.modelo.Salario;

public class FuncionariosService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Funcionario> dao;

	@SuppressWarnings("unchecked")
	public List<Funcionario> pesquisaNomeESalario() {

		String jpql = "select f.codigo, f.nome, f.salario.valor from Funcionario f";

		List<Object[]> resultados = dao.getEntityManager().createQuery(jpql).getResultList();

		List<Funcionario> lista = new ArrayList<Funcionario>();

		for (Object[] objects : resultados) {
			Funcionario funcionario = new Funcionario();
			Salario salario = new Salario();

			funcionario.setCodigo((Long) objects[0]);

			funcionario.setNome((String) objects[1]);

			salario.setValor((BigDecimal) objects[2]);

			funcionario.setSalario(salario);

			lista.add(funcionario);

		}

		return lista;

	}

}
