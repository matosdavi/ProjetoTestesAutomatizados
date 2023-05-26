package br.com.testes.projeto.ProjetoTestesAutomatizados.dto.response;

import lombok.Data;

@Data
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private int quantidade;
}