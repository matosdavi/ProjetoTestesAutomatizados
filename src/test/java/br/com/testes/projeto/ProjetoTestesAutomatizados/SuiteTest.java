package br.com.testes.projeto.ProjetoTestesAutomatizados;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ProdutoControllerTest.class, ProdutoServiceTest.class})
public class SuiteTest {
}