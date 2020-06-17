package com.rh.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Empresa;
import com.rh.util.jsf.FacesUtil;

public class EmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Empresa> dao;

	public void salvar(Empresa empresa) {

		if (verificaSeNomeExiste(empresa.getNome())) {
			FacesUtil.addErrorMessage("Nome já existe");

		} else if (verificaSeCnpjExiste(empresa.getCnpj())) {
			FacesUtil.addErrorMessage("Cnpj já existe");

		} else if (verificaSeNumeroExiste(empresa.getNumeroEmpresa())) {
			FacesUtil.addErrorMessage("Numero já existe");
		} else {
			dao.salvar(empresa);
			FacesUtil.addSuccessMessage("Salvo");

		}

	}

	private boolean verificaSeNumeroExiste(Long numeroEmpresa) {
		List<Empresa> consulta = dao.getEntityManager()
				.createQuery("select c from Empresa c where numeroEmpresa = ?", Empresa.class)
				.setParameter(1, numeroEmpresa).getResultList();

		for (Empresa empresa : consulta) {
			empresa.getNome();
			return true;
		}
		return false;
	}

	public boolean verificaSeNomeExiste(String nome) {

		List<Empresa> consulta = dao.getEntityManager()
				.createQuery("select c from Empresa c where nome = ?", Empresa.class).setParameter(1, nome)
				.getResultList();

		for (Empresa empresa : consulta) {
			empresa.getNome();
			return true;
		}
		return false;

	}

	public boolean verificaSeCnpjExiste(String cnpj) {

		List<Empresa> consulta = dao.getEntityManager()
				.createQuery("select c from Empresa c where cnpj = ?", Empresa.class).setParameter(1, cnpj)
				.getResultList();

		for (Empresa empresa : consulta) {
			empresa.getNome();
			return true;
		}
		return false;

	}

	public List<Empresa> buscarTodasEmpresas() {

		return dao.listarTodos(Empresa.class);
	}

}
