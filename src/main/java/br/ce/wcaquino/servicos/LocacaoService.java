package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import buildermaster.BuilderMaster;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
		if (usuario == null){
			throw new LocadoraException("usuario vazio");
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		if (filmes == null) throw new LocadoraException("filme vazio");
		Double filmesPrecoTotal = 0d;




		for (int i = 0; i < filmes.size(); i++) {
			Filme filme = filmes.get(i);
			if (filme.getEstoque() == 0){
				throw new FilmeSemEstoqueException();
			}
			Double precoFilme = filme.getPrecoLocacao();
			switch (i + 1) {
				case 3 -> precoFilme -= precoFilme * 0.25d;
				case 4 -> precoFilme -= precoFilme * 0.50d;
				case 5 -> precoFilme -= precoFilme * 0.75d;
				case 6 -> precoFilme = 0d;
			}
			filmesPrecoTotal += precoFilme;
		}

		locacao.setValor(filmesPrecoTotal);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY))
			dataEntrega = adicionarDias(dataEntrega, 1);
		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

	public static void main(String[] args){
		new BuilderMaster().gerarCodigoClasse(Locacao.class);
	}

}