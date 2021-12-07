package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;


@RunWith(Parameterized.class)
public class CalcularValorLocacaoTest {

    @Parameter
    public List<Filme> filmes;

    @Parameter(value=1)
    public Double valorLocacao;

    @Parameter(value = 2)
    public String  cenario;

    private LocacaoService service;



    @Before
    public void setup(){service = new LocacaoService();}

    private static Filme filme1 = FilmeBuilder.umFilme().agora();
    private static Filme filme2 = FilmeBuilder.umFilme().agora();
    private static Filme filme3 = FilmeBuilder.umFilme().agora();
    private static Filme filme4 = FilmeBuilder.umFilme().agora();
    private static Filme filme5 = FilmeBuilder.umFilme().agora();
    private static Filme filme6 = FilmeBuilder.umFilme().agora();
    private static Filme filme7 = FilmeBuilder.umFilme().agora();

    @Parameters(name="{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][]{
                {Arrays.asList(filme1, filme2), 8d, "2 Filmes Sem desconto"},
                {Arrays.asList(filme1, filme2, filme3), 11d, "3 Filmes 25%"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13d, "4 Filmes 50%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14d, "5 filmes 75%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14d, "6 filmes 100%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18d, "7 filmes Sem desconto"},
        });

    }

    @Test
    public void deveCalcularValorLocacaoDescontos() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //validacao
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(valorLocacao));

    }

}
