package br.com.testes.projeto.ProjetoTestesAutomatizados.controller;

import br.com.testes.projeto.ProjetoTestesAutomatizados.dto.request.ProdutoSalvarRequestDTO;
import br.com.testes.projeto.ProjetoTestesAutomatizados.dto.response.ProdutoResponseDTO;
import br.com.testes.projeto.ProjetoTestesAutomatizados.dto.response.ProdutoSalvarResponseDTO;
import br.com.testes.projeto.ProjetoTestesAutomatizados.model.Produto;
import br.com.testes.projeto.ProjetoTestesAutomatizados.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.testes.projeto.ProjetoTestesAutomatizados.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    final ProdutoService produtoService;
    final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoSalvarResponseDTO create(@RequestBody ProdutoSalvarRequestDTO requestDTO) {

        Produto produto = new Produto();
        produto.setNome(requestDTO.getNome());
        produto.setQuantidade(requestDTO.getQuantidade());

        produto = produtoService.salvar(produto);

        ProdutoSalvarResponseDTO responseDTO = new ProdutoSalvarResponseDTO();
        responseDTO.setId(produto.getId());
        responseDTO.setNome(produto.getNome());
        responseDTO.setQuantidade(produto.getQuantidade());

        return responseDTO;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> read() {

        List<ProdutoResponseDTO> responseDTOList = new ArrayList<>();

        List<Produto> produtoList = produtoService.listar();

        produtoList.forEach(produto -> {
            ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
            produtoResponseDTO.setId(produto.getId());
            produtoResponseDTO.setNome(produto.getNome());
            produtoResponseDTO.setQuantidade(produto.getQuantidade());
            responseDTOList.add(produtoResponseDTO);
        });

        return responseDTOList;
    }

    @PutMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoSalvarResponseDTO update(@PathVariable Long idProduto, @RequestBody ProdutoSalvarRequestDTO requestDTO) {

        Produto produto = new Produto();
        produto.setId(idProduto);
        produto.setNome(requestDTO.getNome());
        produto.setQuantidade(requestDTO.getQuantidade());

        produto = produtoService.salvar(produto);

        ProdutoSalvarResponseDTO responseDTO = new ProdutoSalvarResponseDTO();
        responseDTO.setId(produto.getId());
        responseDTO.setNome(produto.getNome());
        responseDTO.setQuantidade(produto.getQuantidade());

        return responseDTO;
    }

    @DeleteMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long idProduto) {
        produtoService.excluir(idProduto);
    }

    @GetMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO getById(@PathVariable Long idProduto) {

        Produto produto = produtoService.getById(idProduto);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(produto.getId());
        produtoResponseDTO.setNome(produto.getNome());
        produtoResponseDTO.setQuantidade(produto.getQuantidade());

        return produtoResponseDTO;
    }
}