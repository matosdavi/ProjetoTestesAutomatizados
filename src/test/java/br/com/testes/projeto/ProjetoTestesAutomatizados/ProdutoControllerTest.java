package br.com.testes.projeto.ProjetoTestesAutomatizados;

import br.com.testes.projeto.ProjetoTestesAutomatizados.dto.request.ProdutoSalvarRequestDTO;
import br.com.testes.projeto.ProjetoTestesAutomatizados.dto.response.ProdutoResponseDTO;
import br.com.testes.projeto.ProjetoTestesAutomatizados.dto.response.ProdutoSalvarResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void create_produto() {

        ProdutoSalvarRequestDTO requestDTO = new ProdutoSalvarRequestDTO();

        requestDTO.setNome("produto test");
        requestDTO.setQuantidade(1);

        ResponseEntity<ProdutoSalvarResponseDTO> responseDTO =
                restTemplate.exchange(
                "/produtos",
                HttpMethod.POST,
                new HttpEntity<>(requestDTO),
                ProdutoSalvarResponseDTO.class);

        Assertions.assertEquals(201, responseDTO.getStatusCode().value());
        Assertions.assertNotNull(responseDTO.getBody());
        Assertions.assertNotNull(responseDTO.getBody().getId());
        Assertions.assertEquals(requestDTO.getNome(), responseDTO.getBody().getNome());
        Assertions.assertEquals(requestDTO.getQuantidade(), responseDTO.getBody().getQuantidade());
    }

    @Test
    void read_produto() {
        ProdutoSalvarRequestDTO requestDTO = new ProdutoSalvarRequestDTO();
        requestDTO.setNome("produto test");
        requestDTO.setQuantidade(1);

        ResponseEntity<ProdutoSalvarResponseDTO> responseDTO =
                restTemplate.exchange(
                        "/produtos",
                        HttpMethod.POST,
                        new HttpEntity<>(requestDTO),
                        ProdutoSalvarResponseDTO.class);

        Long idProduto = responseDTO.getBody().getId();

        requestDTO = new ProdutoSalvarRequestDTO();
        requestDTO.setNome("produto test2");
        requestDTO.setQuantidade(1);

        responseDTO =
                restTemplate.exchange(
                        "/produtos",
                        HttpMethod.POST,
                        new HttpEntity<>(requestDTO),
                        ProdutoSalvarResponseDTO.class);

        Long idProduto2 = responseDTO.getBody().getId();

        ResponseEntity<List<ProdutoResponseDTO>> responseGetDTO =
                restTemplate.exchange(
                        "/produtos",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProdutoResponseDTO>>() {});

        List<ProdutoResponseDTO> produtoList = responseGetDTO.getBody();

        Assertions.assertFalse(produtoList.isEmpty());
        Assertions.assertTrue(
                produtoList.stream().anyMatch(produtoResponseDTO -> produtoResponseDTO.getId().equals(idProduto))
        );
        Assertions.assertTrue(
                produtoList.stream().anyMatch(produtoResponseDTO -> produtoResponseDTO.getId().equals(idProduto2))
        );
    }

    @Test
    void update_produto() {

        ProdutoSalvarRequestDTO requestDTO = new ProdutoSalvarRequestDTO();
        requestDTO.setNome("produto test");
        requestDTO.setQuantidade(1);

        ResponseEntity<ProdutoSalvarResponseDTO> responseDTO =
                restTemplate.exchange(
                "/produtos",
                HttpMethod.POST,
                new HttpEntity<>(requestDTO),
                ProdutoSalvarResponseDTO.class);

        Long idProduto = responseDTO.getBody().getId();

        requestDTO.setNome("nome atualizado");
        requestDTO.setQuantidade(2);

        ResponseEntity<ProdutoSalvarResponseDTO> responsePutDTO =
                restTemplate.exchange(
                        "/produtos/" + idProduto,
                        HttpMethod.PUT,
                        new HttpEntity<>(requestDTO),
                        ProdutoSalvarResponseDTO.class);

        Assertions.assertEquals(200, responsePutDTO.getStatusCode().value());
        Assertions.assertEquals(idProduto, responsePutDTO.getBody().getId());
        Assertions.assertEquals(requestDTO.getNome(), responsePutDTO.getBody().getNome());
        Assertions.assertEquals(requestDTO.getQuantidade(), responsePutDTO.getBody().getQuantidade());
    }

    @Test
    void delete_cliente() {

        ProdutoSalvarRequestDTO requestDTO = new ProdutoSalvarRequestDTO();
        requestDTO.setNome("cliente test");
        requestDTO.setQuantidade(1);

        ResponseEntity<ProdutoSalvarResponseDTO> responseDTO =
                restTemplate.exchange(
                        "/produtos",
                        HttpMethod.POST,
                        new HttpEntity<>(requestDTO),
                        ProdutoSalvarResponseDTO.class);

        Long idProduto = responseDTO.getBody().getId();

        ResponseEntity<?> responseDeleteDTO =
                restTemplate.exchange(
                        "/produtos/" + idProduto,
                        HttpMethod.DELETE,
                        null,
                        Object.class
                );

        Assertions.assertEquals(202, responseDeleteDTO.getStatusCode().value());

        ResponseEntity<ProdutoResponseDTO> responseGetDTO =
                restTemplate.exchange(
                        "/produtos/" + idProduto,
                        HttpMethod.GET,
                        null,
                        ProdutoResponseDTO.class);

        Assertions.assertEquals(404, responseGetDTO.getStatusCode().value());
    }

    @Test
    void get_cliente_by_id() {

        ProdutoSalvarRequestDTO requestDTO = new ProdutoSalvarRequestDTO();
        requestDTO.setNome("produto test");
        requestDTO.setQuantidade(1);

        ResponseEntity<ProdutoSalvarResponseDTO> responseDTO =
                restTemplate.exchange(
                        "/produtos",
                        HttpMethod.POST,
                        new HttpEntity<>(requestDTO),
                        ProdutoSalvarResponseDTO.class);

        Long idProduto = responseDTO.getBody().getId();

        ResponseEntity<ProdutoResponseDTO> responseGetDTO =
                restTemplate.exchange(
                        "/produtos/" + idProduto,
                        HttpMethod.GET,
                        null,
                        ProdutoResponseDTO.class);

        ProdutoResponseDTO responseBody = responseGetDTO.getBody();

        Assertions.assertEquals(200, responseGetDTO.getStatusCode().value());
        Assertions.assertEquals(idProduto, responseBody.getId());
        Assertions.assertEquals(requestDTO.getNome(), responseBody.getNome());
        Assertions.assertEquals(requestDTO.getQuantidade(), responseBody.getQuantidade());
    }
}