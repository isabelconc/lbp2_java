package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.itens.Naipe;
import br.edu.ifsp.spo.java.cards.itens.Valor;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PontuadorClassicoTest {
    @Test
    void verificarPontuacaoQuandoListaDeCartasENula(){
        //Classe sob teste
        var pontuador = new PontuadorClassico();

        //1. Configuração do cenário
        List<Carta> cartas = null;
        var pontuacaoEsperada = 0;

        //2. Execução
        var pontuacaoObtida = pontuador.verificarPontuacao(cartas);

        //3. Verificação
        assertEquals(pontuacaoEsperada, pontuacaoObtida);
    }

    @Test
    void verificarPontuacaoQuandoListaDeCartasEVazia(){
        //Classe sob teste
        var pontuador = new PontuadorClassico();

        //1. Configuração do cenário
        List<Carta> cartas = new ArrayList<>();
        var pontuacaoEsperada = 0;

        //2. Execução
        var pontuacaoObtida = pontuador.verificarPontuacao(cartas);

        //3. Verificação
        assertEquals(pontuacaoEsperada, pontuacaoObtida);
    }

    @Test
    void verificarPontuacaoQuandoListaDeCartasContemCartas(){
        // TODO: Este cenário possui um problema. Você consegue identificar?
        //  Dica: Altere o código do "verificarPontuacao", trocando o "+=" por "=" em
        //  "case DOIS -> pontuacao += 2;" e veja o que acontece

        // O problema é que o teste unitário não contempla todas as possibilidades de cartas,
        // permitindo que o teste seja validado, mesmo com um erro lógico no código.
        // A solução é aplicar cenários onde todas as cartas são testadas, garantindo que
        // erros relacionados as cartas sejam cobertos.

        // Teste antigo

//        //Classe sob teste '
//        var pontuador = new PontuadorClassico();
//
//        //1. Configuração do cenário
//        List<Carta> cartas = new ArrayList<>(Arrays.asList(
//                new Carta(Naipe.COPAS, Valor.DEZ),
//                new Carta(Naipe.ESPADAS, Valor.SETE),
//                new Carta(Naipe.OUROS, Valor.AS)
//        ));
//        var pontuacaoEsperada = 18;
//
//        //2. Execução
//        var pontuacaoObtida = pontuador.verificarPontuacao(cartas);
//
//        //3. Verificação
//        assertEquals(pontuacaoEsperada, pontuacaoObtida);
//   }


        // Teste novo: soma todas as cartas (85) de forma que nenhuma não seja levada em conta
        // no teste

            //Classe sob teste '
            var pontuador = new PontuadorClassico();

            // 1. Configuração do cenário
            List<Carta> cartas = new ArrayList<>(Arrays.asList(
                    new Carta(Naipe.COPAS, Valor.AS),
                    new Carta(Naipe.ESPADAS, Valor.DOIS),
                    new Carta(Naipe.OUROS, Valor.TRES),
                    new Carta(Naipe.PAUS, Valor.QUATRO),
                    new Carta(Naipe.COPAS, Valor.CINCO),
                    new Carta(Naipe.ESPADAS, Valor.SEIS),
                    new Carta(Naipe.OUROS, Valor.SETE),
                    new Carta(Naipe.PAUS, Valor.OITO),
                    new Carta(Naipe.COPAS, Valor.NOVE),
                    new Carta(Naipe.ESPADAS, Valor.DEZ),
                    new Carta(Naipe.OUROS, Valor.VALETE),
                    new Carta(Naipe.PAUS, Valor.DAMA),
                    new Carta(Naipe.COPAS, Valor.REI)
            ));

            var pontuacaoEsperada = 85;

            // 2. Execução
            var pontuacaoObtida = pontuador.verificarPontuacao(cartas);

            // 3. Verificação
            assertEquals(pontuacaoEsperada, pontuacaoObtida);
        };


        @Test
    void calcularPontuacaoRodadaQuandoEmpate(){
        var pontuador = new PontuadorClassico();

        //1. Configuração
        var conjuntoCartasNaoEstourado1 = new ArrayList<>(Arrays.asList(
                new Carta(Naipe.COPAS, Valor.DEZ),
                new Carta(Naipe.ESPADAS, Valor.SETE),
                new Carta(Naipe.OUROS, Valor.AS)
        ));

        var conjuntoCartasNaoEstourado2 = new ArrayList<>(Arrays.asList(
                new Carta(Naipe.COPAS, Valor.SEIS),
                new Carta(Naipe.ESPADAS, Valor.SEIS),
                new Carta(Naipe.OUROS, Valor.SEIS)
        ));

        var jogador1 = new Jogador("Crash Test Dummy 1");
        conjuntoCartasNaoEstourado1.forEach(jogador1::receberCarta);

        var jogador2 = new Jogador("Crash Test Dummy 2");
        conjuntoCartasNaoEstourado2.forEach(jogador2::receberCarta);

        var resultadoEsperado = new HashMap<>(Map.of(
                jogador1, 10,
                jogador2, 10
        ));

        //2. Execução
        var resultadoObtido = pontuador.calcularPontuacaoRodada(jogador1, jogador2);

        //3. Asserção / Verificação
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    void calcularPontuacaoRodadaQuandoUmEstouraOutroNao() {
        var pontuador = new PontuadorClassico();

        var jogador1 = new Jogador("Jogador 1");
        jogador1.receberCarta(new Carta(Naipe.COPAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.ESPADAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.OUROS, Valor.TRES));

        var jogador2 = new Jogador("Jogador 2");
        jogador2.receberCarta(new Carta(Naipe.COPAS, Valor.NOVE));
        jogador2.receberCarta(new Carta(Naipe.ESPADAS, Valor.NOVE));

        var esperado = Map.of(jogador1, -5, jogador2, 18);

        var obtido = pontuador.calcularPontuacaoRodada(jogador1, jogador2);

        assertEquals(esperado, obtido);
    }

    @Test
    void calcularPontuacaoRodadaQuandoAmbosEstouraram() {
        var pontuador = new PontuadorClassico();

        var jogador1 = new Jogador("Jogador 1");
        jogador1.receberCarta(new Carta(Naipe.COPAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.ESPADAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.OUROS, Valor.DOIS));

        var jogador2 = new Jogador("Jogador 2");
        jogador2.receberCarta(new Carta(Naipe.COPAS, Valor.DEZ));
        jogador2.receberCarta(new Carta(Naipe.ESPADAS, Valor.DEZ));
        jogador2.receberCarta(new Carta(Naipe.OUROS, Valor.TRES));

        var esperado = Map.of(jogador1, -1, jogador2, -2);

        var obtido = pontuador.calcularPontuacaoRodada(jogador1, jogador2);

        assertEquals(esperado, obtido);
    }

    @Test
    void calcularPontuacaoRodadaQuandoAmbosFazem21() {
        var pontuador = new PontuadorClassico();

        var jogador1 = new Jogador("Jogador 1");
        jogador1.receberCarta(new Carta(Naipe.COPAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.ESPADAS, Valor.AS));
        jogador1.receberCarta(new Carta(Naipe.OUROS, Valor.DEZ));

        var jogador2 = new Jogador("Jogador 2");
        jogador2.receberCarta(new Carta(Naipe.COPAS, Valor.DEZ));
        jogador2.receberCarta(new Carta(Naipe.ESPADAS, Valor.AS));
        jogador2.receberCarta(new Carta(Naipe.OUROS, Valor.DEZ));

        var esperado = Map.of(jogador1, 21, jogador2, 21);

        var obtido = pontuador.calcularPontuacaoRodada(jogador1, jogador2);

        assertEquals(esperado, obtido);
    }

    @Test
    void calcularPontuacaoRodadaQuandoUmFaz21OutroNao() {
        var pontuador = new PontuadorClassico();

        var jogador1 = new Jogador("Jogador 1");
        jogador1.receberCarta(new Carta(Naipe.COPAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.ESPADAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.OUROS, Valor.AS));

        var jogador2 = new Jogador("Jogador 2");
        jogador2.receberCarta(new Carta(Naipe.COPAS, Valor.NOVE));
        jogador2.receberCarta(new Carta(Naipe.ESPADAS, Valor.AS));

        var esperado = Map.of(jogador1, 30, jogador2, 0);

        var obtido = pontuador.calcularPontuacaoRodada(jogador1, jogador2);

        assertEquals(esperado, obtido);
    }

    @Test
    void calcularPontuacaoRodadaQuandoAmbosAbaixoDe21() {
        var pontuador = new PontuadorClassico();

        var jogador1 = new Jogador("Jogador 1");
        jogador1.receberCarta(new Carta(Naipe.COPAS, Valor.DEZ));
        jogador1.receberCarta(new Carta(Naipe.ESPADAS, Valor.SEIS));

        var jogador2 = new Jogador("Jogador 2");
        jogador2.receberCarta(new Carta(Naipe.COPAS, Valor.NOVE));
        jogador2.receberCarta(new Carta(Naipe.COPAS, Valor.AS));

        var esperado = Map.of(jogador1, 6, jogador2, 0);

        var obtido = pontuador.calcularPontuacaoRodada(jogador1, jogador2);

        assertEquals(esperado, obtido);
    }




}