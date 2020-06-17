package com.rh.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.rh.util.jpa.Transactional;

@Transactional
public class GenericDAO<E> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public void salvar(E entidade) {

		entityManager.merge(entidade);

	}

	public E updateMerge(E entidade) {

		E entidadeSalva = entityManager.merge(entidade);

		return entidadeSalva;

	}

	@SuppressWarnings("unchecked")
	public E pesquisar(Long id, Class<?> entidade) {

		E e = (E) entityManager.find(entidade.getClass(), id);

		return e;

	}

	@SuppressWarnings("unchecked")
	public List<E> listarTodos(Class<E> entidade) {

		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();

		return lista;

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public E buscarPorId(Class<E> entidade, Long codigo) {

		return entityManager.find(entidade, codigo);

	}

}