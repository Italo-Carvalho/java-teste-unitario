package br.ce.wcaquino.servicos;


import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.matchers.DiaSemanaMatcher;
import br.ce.wcaquino.matchers.MatchersProprios;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private LocacaoService service;

    @Before //antes de cada teste
    public void setup() {
        service = new LocacaoService();
    }

    @After //depois de cada teste
    public void tearDown() {
    }


    @Test
    public void testeLocacao() throws Exception {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY)); //não executa sabado

        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        //acao
        Locacao locacao = service.alugarFilme(usuario, Arrays.asList(FilmeBuilder.umFilme().comValor(5d).agora()));

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getDataLocacao(), MatchersProprios.ehHoje());
        error.checkThat(locacao.getDataRetorno(), MatchersProprios.obterDataComDiferencaDias(1));
        //error.checkThat(locacao.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void testLocacao_filmeSemEstoque() throws Exception {
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilmeSemEstoque().agora());

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
        //scenario
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());

        //action
        try {
            service.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), is("usuario vazio"));
        }
    }

    @Test
    public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
        //scenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        //action
        exception.expect(LocadoraException.class);
        exception.expectMessage("filme vazio");
        service.alugarFilme(usuario, null);
    }




}
