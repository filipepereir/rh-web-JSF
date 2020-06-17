package com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Empresa;
import com.rh.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter {

	private GenericDAO<Empresa> empresaDAO;

	@SuppressWarnings("unchecked")
	public EmpresaConverter() {
		this.empresaDAO = CDIServiceLocator.getBean(GenericDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Empresa retorno = null;

		if (value != null) {
			retorno = this.empresaDAO.buscarPorId(Empresa.class, new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((Empresa) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
