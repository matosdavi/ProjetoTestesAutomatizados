package br.com.testes.projeto.ProjetoTestesAutomatizados.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nome;

    @Column(nullable = false)
    private Integer quantidade;

}