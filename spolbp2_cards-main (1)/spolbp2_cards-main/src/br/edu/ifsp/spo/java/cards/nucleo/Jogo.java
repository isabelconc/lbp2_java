package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.*;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.ui.JogoUI;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Optional;

public class Jogo {

    private Baralho baralho;
    private Jogador jogador1;
    private Jogador jogador2;
    private int PontuacaodaVez1;
    private int PontuacaodaVez2;

    private Pontuador pontuador;
    private int partidas;

    private JogoUI ui;

    public Jogo() {
        this.ui = new JogoUI();

        this.pontuador = this.ui.escolherPontuador();

        this.baralho = new Baralho();
        this.jogador1 = new Jogador(ui.solicitarNomeJogador(1));
        this.jogador2 = new JogadorAI(); // ou ui.solicitarNomeJogador(2)
        this.PontuacaodaVez1 = 0;
        this.PontuacaodaVez2 = 0;

        this.partidas = 0;
    }

    public void play() {
        this.partidas = ui.Partidas();
        jogoRodada();
    }

    private void jogoRodada() {
        for (int i = 0; i < this.partidas; i++) {
            ui.exibirInicioJogo();

            prepararCartas();

            executarRodada(this.jogador1);
            executarRodada(this.jogador2);

            ResultadosPossiveis resultado1 = OResultado(this.jogador1);
            ResultadosPossiveis resultado2 = OResultado(this.jogador2);

            boolean empate = resultado1 == resultado2;
            pontuarPartida(resultado1, resultado2, empate);

            Optional<Jogador> vencedorRodada = verificarVencedor();

            if (vencedorRodada.isPresent()) {
                ui.exibirVencedor(vencedorRodada.get());
            } else {
                ui.exibirEmpate();
            }

            reiniciarRodada();
        }

        Optional<Jogador> vencedorPartida = vencedorPartida();

        if (vencedorPartida.isPresent()) {
            ui.exibirVencedorRodada(vencedorPartida.get(), this.PontuacaodaVez1, this.PontuacaodaVez2);
        } else {
            ui.exibirEmpate();
        }
    }

    private ResultadosPossiveis OResultado(Jogador jogador) {
        int pontuacao = this.pontuador.verificarPontuacao(jogador.getMao());

        if (pontuacao > 21) {
            return ResultadosPossiveis.ESTOURO;
        } else if (pontuacao < 21) {
            return ResultadosPossiveis.MENOS;
        } else {
            return ResultadosPossiveis.VINTEUM;
        }
    }

    private void executarRodada(Jogador jogador) {
        ui.exibirInicioRodada(jogador.getNome());

        AcaoDoJogador acao;

        do {
            int pontuacao = this.pontuador.verificarPontuacao(jogador.getMao());

            if (jogador instanceof JogadorAI ia) {
                ui.exibirMao(ia.getMao(), pontuacao);
                acao = ia.decidir(pontuacao);
            } else {
                ui.exibirMao(jogador.getMao(), pontuacao);
                acao = ui.obterAcao();
            }

            if (acao == AcaoDoJogador.COMPRAR) {
                jogador.receberCarta(this.baralho.tirarCarta());
            }

        } while (acao == AcaoDoJogador.COMPRAR);

        ui.exibirFimRodada(jogador.getNome());
    }

    private Optional<Jogador> verificarVencedor() {
        int pont1 = pontuador.verificarPontuacao(jogador1.getMao());
        int pont2 = pontuador.verificarPontuacao(jogador2.getMao());

        boolean ambosEstouraram = pont1 > 21 && pont2 > 21;
        boolean empate = pont1 == pont2;

        if (ambosEstouraram || empate) {
            return Optional.empty();
        }

        if (pont1 > 21) return Optional.of(jogador2);
        if (pont2 > 21) return Optional.of(jogador1);

        return Optional.of(pont1 > pont2 ? jogador1 : jogador2);
    }

    private void reiniciarRodada() {
        baralho.adicionarDescartes(jogador1.descartarMao());
        baralho.adicionarDescartes(jogador2.descartarMao());
    }

    private void prepararCartas() {
        jogador1.receberCarta(baralho.tirarCarta());
        jogador2.receberCarta(baralho.tirarCarta());
        jogador1.receberCarta(baralho.tirarCarta());
        jogador2.receberCarta(baralho.tirarCarta());
    }

    private Optional<Jogador> vencedorPartida() {
        if (this.PontuacaodaVez1 > this.PontuacaodaVez2) {
            return Optional.of(jogador1);
        } else if (this.PontuacaodaVez2 > this.PontuacaodaVez1) {
            return Optional.of(jogador2);
        } else {
            return Optional.empty();
        }
    }

    private void pontuarPartida(ResultadosPossiveis res1, ResultadosPossiveis res2, boolean empate) {
        int mao1 = pontuador.verificarPontuacao(jogador1.getMao());
        int mao2 = pontuador.verificarPontuacao(jogador2.getMao());

        if (empate) {
            PontuacaodaVez1 += 10;
            PontuacaodaVez2 += 10;
            return;
        }

        if (res1 == ResultadosPossiveis.VINTEUM) PontuacaodaVez1 += 30;
        else if (res2 == ResultadosPossiveis.ESTOURO) PontuacaodaVez1 += mao1;

        if (res2 == ResultadosPossiveis.VINTEUM) PontuacaodaVez2 += 30;
        else if (res1 == ResultadosPossiveis.ESTOURO) PontuacaodaVez2 += mao2;
    }

    @Override
    public String toString() {
        return "Jogo de Baralho Genérico" +
                "\nJogadores:" +
                "\n" + jogador1 +
                "\nPontuação jogador 1: " + pontuador.verificarPontuacao(jogador1.getMao()) +
                "\n" + jogador2 +
                "\nPontuação jogador 2: " + pontuador.verificarPontuacao(jogador2.getMao());
    }
}
