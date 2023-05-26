package br.com.testes.projeto.ProjetoTestesAutomatizados.repository;

import br.com.testes.projeto.ProjetoTestesAutomatizados.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}