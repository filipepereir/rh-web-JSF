import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.rh.modelo.Funcionario;
import com.rh.modelo.Status;

public class TesteTyped {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("rhPU");
	EntityManager manager = factory.createEntityManager();

	@Test
	public void testeTyped() {

		try {
			TypedQuery<Funcionario> resultado = null;

			resultado = manager.createQuery("from Funcionario", Funcionario.class);

			resultado.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void Teste2Typed() {

		List<Funcionario> resultados = manager.createQuery("from Funcionario").getResultList();

		resultados.forEach(item -> System.out
				.println("Nome: " + item.getNome() + " Observação: " + item.getContratacao().getObservacao()));

	}

	@Test
	public void listaENUM() {

		List<Status> lista = new ArrayList<Status>();

		lista = Arrays.asList(Status.values());

		lista.forEach(item -> System.out.println(item));
	}

}
