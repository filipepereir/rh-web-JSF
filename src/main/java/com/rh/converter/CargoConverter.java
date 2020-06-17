package com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Cargo;
import com.rh.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cargo.class)
public class CargoConverter implements Converter {

	private GenericDAO<Cargo> cargoDAO;

	@SuppressWarnings("unchecked")
	public CargoConverter() {
		this.cargoDAO = CDIServiceLocator.getBean(GenericDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cargo retorno = null;

		if (value != null) {
			retorno = this.cargoDAO.buscarPorId(Cargo.class, new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((Cargo) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
