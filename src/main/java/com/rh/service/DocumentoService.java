package com.rh.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import com.rh.dao.GenericDAO;
import com.rh.modelo.Documento;
import com.rh.modelo.Funcionario;
import com.rh.util.jsf.FacesUtil;

public class DocumentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private GenericDAO dao;

	public void salvarDocumento(FileUploadEvent fileUploadEvent) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		String numero = facesContext.getExternalContext().getRequestParameterMap().get("uploadForm:codigoFuncionario");

		if (!numero.equalsIgnoreCase("")) {

			Long codigoFuncionario = Long.parseLong(numero);

			Funcionario funcionario = funcionarioService.pesquisaFuncionario(codigoFuncionario);

			if (funcionario != null) {

				Documento documento = new Documento();

				File file = new File(Documento.getCaminhoImagens() + numero + "\\");

				file.mkdir();

				String caminho = file.getPath();

				documento.setNomeArquivo(fileUploadEvent.getFile().getFileName());

				documento.setCaminho(caminho);
				documento.setFuncionario(funcionario);

				dao.salvar(documento);

				try {
					copyFile(fileUploadEvent.getFile().getFileName(), fileUploadEvent.getFile().getInputstream(),
							caminho + "\\");
					FacesUtil.addSuccessMessage("Upload Feito para o codigo Funcionario : " + numero);
				} catch (IOException e) {
					FacesUtil.addErrorMessage(e.getMessage());
				}

			} else {
				FacesUtil.addErrorMessage("Esse Codigo de Funcionario não existe");
			}

		} else {
			FacesUtil.addErrorMessage("Codigo do Funcionario nao pode ser em Branco");
		}

	}

	public void copyFile(String fileName, InputStream in, String destination) {
		try {

			if (in != null) {
				// write the inputStream to a FileOutputStream
				OutputStream out = new FileOutputStream(new File(destination + fileName));
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = in.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				in.close();
				out.flush();
				out.close();
				System.out.println("New file created!");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Documento> listarDocumentos(Documento documento) {

		List<Documento> lista = new ArrayList<Documento>();

		File file = new File(Documento.getCaminhoImagens() + documento.getCodigo() + "\\");

		Funcionario funcionario = (Funcionario) dao.buscarPorId(Funcionario.class, documento.getCodigo());

		if (file.isDirectory()) {

			String[] arqs = file.list();
			for (String nome : arqs) {
				Documento upload = new Documento();

				upload.setNomeArquivo(nome);
				upload.setFuncionario(funcionario);
				lista.add(upload);
			}

		}
		return lista;
	}

	public Documento buscarFuncionarioCodigo(Long codigo) {
		Documento documento = (Documento) dao.getEntityManager()
				.createQuery("from Documento where funcionario_codigo = :codigo").setParameter("codigo", codigo)
				.getSingleResult();

		return documento;
	}

	public boolean verificaCodigoFuncionario(Long codigo) {
		Funcionario funcionario = (Funcionario) dao.buscarPorId(Funcionario.class, codigo);

		if (funcionario != null) {
			return true;
		}
		return false;

	}

}
