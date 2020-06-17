package com.rh.controller;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import com.rh.service.DocumentoService;

@Named(value = "cadastroDocumentoBean")
@SessionScoped
public class CadastroDocumentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DocumentoService documentoService;

	private Long codigoFuncionario;

	public void enviarArquivo(FileUploadEvent fileUploadEvent) {

		documentoService.salvarDocumento(fileUploadEvent);

	}

	public Long getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(Long codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

}
