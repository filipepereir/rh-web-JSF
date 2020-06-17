package com.rh.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.rh.modelo.Documento;
import com.rh.service.DocumentoService;
import com.rh.util.jsf.FacesUtil;

@Named(value = "pesquisaDocumentoBean")
@ViewScoped
public class PesquisaDocumentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private StreamedContent file;

	@Inject
	private DocumentoService documentoService;

	private Documento documento;
	private List<Documento> listaDocumentosDiretorio;

	public PesquisaDocumentoBean() {
		documento = new Documento();
		listaDocumentosDiretorio = new ArrayList<Documento>();

	}

	public void listarDocumentosFuncionario() {

		listaDocumentosDiretorio = documentoService.listarDocumentos(documento);

	}

	public void downloadDocumento(String documento, Long codigo) {

		try {
			InputStream inputStream = new FileInputStream(
					Documento.getCaminhoImagens() + "\\" + codigo + "\\" + documento);
			file = new DefaultStreamedContent(inputStream, "application/pdf", documento);
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Documento> getListaDocumentosDiretorio() {
		return listaDocumentosDiretorio;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public void setListaDocumentosDiretorio(List<Documento> listaDocumentosDiretorio) {
		this.listaDocumentosDiretorio = listaDocumentosDiretorio;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
