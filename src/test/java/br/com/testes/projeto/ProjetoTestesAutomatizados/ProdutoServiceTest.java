package br.com.testes.projeto.ProjetoTestesAutomatizados;

import br.com.testes.projeto.ProjetoTestesAutomatizados.model.Produto;
import br.com.testes.projeto.ProjetoTestesAutomatizados.service.ProdutoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Test
    @DisplayName("Test - Inserir Produto - OK!")
    void create_produto() {

        Produto produto = new Produto();
        produto.setNome("produto test");
        produto.setQuantidade(1);

        Produto produtoSaved = produtoService.salvar(produto);

        Assertions.assertNotNull(produtoSaved.getId());
        Assertions.assertEquals(produto.getNome(), produtoSaved.getNome());
        Assertions.assertEquals(produto.getQuantidade(), produtoSaved.getQuantidade());
    }

    @Test
    void delete_produto() {

        Produto produto = new Produto();
        produto.setNome("Cliente test");
        produto.setQuantidade(1);

        Produto produtoSaved = produtoService.salvar(produto);
        Long idProduto = produtoSaved.getId();
        produtoService.excluir(idProduto);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            Produto produtoDeleted = produtoService.getById(idProduto);
        });
        Assertions.assertEquals("ID não encontrado.", exception.getMessage());
    }

    @Test
    void get_id_invalido() {

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            Produto produto = produtoService.getById(100);
        });
        Assertions.assertEquals("ID não encontrado.", exception.getMessage());
    }

    @Test
    void nome_obrigatorio() {

        Produto produto = new Produto();
        produto.setQuantidade(1);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            Produto produtoSaved = produtoService.salvar(produto);
        });
        Assertions.assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void quantidade_obrigatoria() {

        Produto produto = new Produto();
        produto.setNome("produto test");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            Produto produtoSaved = produtoService.salvar(produto);
        });
        Assertions.assertEquals("A quantidade é obrigatória.", exception.getMessage());
    }

    @Test
    void get_by_id() {

        Produto produto = new Produto();
        produto.setNome("client test");
        produto.setQuantidade(1);

        Produto produtoSaved = produtoService.salvar(produto);

        Long idProduto = produtoSaved.getId();

        Produto produtoGet = produtoService.getById(idProduto);

        Assertions.assertEquals(produtoSaved.getId(), produtoGet.getId());
        Assertions.assertEquals(produtoSaved.getNome(), produtoGet.getNome());
        Assertions.assertEquals(produtoSaved.getQuantidade(), produtoGet.getQuantidade());
    }
}