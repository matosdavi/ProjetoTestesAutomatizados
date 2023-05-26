package br.com.testes.projeto.ProjetoTestesAutomatizados.dto.response;

import lombok.Data;

@Data
public class ProdutoSalvarResponseDTO {

    private Long id;
    private String nome;
    private int quantidade;
}