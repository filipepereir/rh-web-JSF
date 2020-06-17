import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.rh.modelo.Contratacao;
import com.rh.modelo.Documento;
import com.rh.modelo.Funcionario;
import com.rh.modelo.Status;

public class TesteConsultas {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("rhPU");
	EntityManager manager = factory.createEntityManager();

	@Test
	public void pesquisaTodosFuncionarios() {

		@SuppressWarnings("unchecked")
		List<Funcionario> listar = manager.createQuery("select f from Funcionario f").getResultList();

		for (Funcionario funcionario : listar) {
			System.out.println(funcionario.getNome());

			System.out.println("-----------");
		}
	}

	@Test
	public void pesquisaTodosNomesFuncionarios() {

		List<String> buscaNomesFuncionarios = manager.createQuery("select f.nome from Funcionario f", String.class)
				.getResultList();

		for (String nome : buscaNomesFuncionarios) {

			System.out.println(nome);

		}

	}

	@Test
	public void pesquisaStatus() {
		List<String> buscarNome = manager.createQuery("select c.nome from Funcionario c", String.class).getResultList();

		for (String nome : buscarNome) {
			System.out.println("Nome: " + nome);

		}

	}

	@Test
	public void pesquisaAtivos() {
		@SuppressWarnings("rawtypes")
		List<Enum> listaAtivos = manager
				.createQuery("select c.status from Contratacao c where c.status in (:status) and c.codigo", Enum.class)
				.setParameter("status", Status.ATIVO).getResultList();

		for (@SuppressWarnings("rawtypes")
		Enum contratacao : listaAtivos) {

			System.out.println(contratacao);

		}

	}

	@Test
	public void pesquisa2Ativos() {
		List<Contratacao> listaAtivos = manager
				.createQuery("from Contratacao c where c.status in (:status)", Contratacao.class)
				.setParameter("status", Status.ATIVO).getResultList();

		for (Contratacao contratacao : listaAtivos) {
			System.out.println(contratacao.getCodigo());
			System.out.println(contratacao.getStatus());
		}
	}

	@Test
	public void pesquisaFuncionarioSalario() {

		List<BigDecimal> listaSalarios = manager
				.createQuery("select f.salario.valor from Funcionario f ", BigDecimal.class).getResultList();

		for (BigDecimal lista : listaSalarios) {

			System.out.println(lista);

		}

	}

	public void pesquisaFuncionarioPorCodigo() {

		@SuppressWarnings("unused")
		List<Funcionario> lista = manager.createQuery("from c.nome Funcionario c where", Funcionario.class)
				.getResultList();

	}

	@Test
	public void pesquisaFiltrada() {

		String sql = "select f.nome, f.salario.valor, f.contratacao.status from Funcionario f";

		@SuppressWarnings("unchecked")
		List<Object[]> resultados = manager.createQuery(sql).getResultList();

		for (Object[] objects : resultados) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
			System.out.println(objects[2]);
		}
	}

	@Test
	public Documento buscarFuncionarioCodigo() {
		Documento documento = (Documento) manager.createQuery("from Documento where funcionario_codigo = :codigo")
				.setParameter("codigo", 7).getSingleResult();

		return documento;
	}

}
