package br.com.testes.projeto.ProjetoTestesAutomatizados.service;

import br.com.testes.projeto.ProjetoTestesAutomatizados.dto.response.ProdutoResponseDTO;
import br.com.testes.projeto.ProjetoTestesAutomatizados.exception.NotFoundException;
import br.com.testes.projeto.ProjetoTestesAutomatizados.exception.ValidationException;
import br.com.testes.projeto.ProjetoTestesAutomatizados.repository.ProdutoRepository;
import br.com.testes.projeto.ProjetoTestesAutomatizados.model.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }
    public Produto salvar(Produto produto) {
        if (produto.getNome() == null) {
            throw new ValidationException("O nome é obrigatório.");
        }
        if (produto.getQuantidade() == null) {
            throw new ValidationException("A quantidade é obrigatória.");
        }
        return produtoRepository.save(produto);
    }

    public void excluir(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    public Produto getById(long idProduto) {
        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if (produto == null) {
            throw new NotFoundException("ID não encontrado.");
        }
        return produto;
    }
}