package br.com.testes.projeto.ProjetoTestesAutomatizados.dto.request;

import lombok.Data;

@Data
public class ProdutoSalvarRequestDTO {

    private String nome;
    private int quantidade;
}