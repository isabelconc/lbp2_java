package br.edu.ifsp.spo.java.cards;

import java.util.ArrayList;

public class Jogo {

    private final Jogador jogador1;
    private final Jogador jogador2;
    private final Baralho baralho;

    public Jogo(int tamanhoInicialMao){
        this.baralho = new Baralho(); // inicialização
        this.jogador1 = new Jogador("João");
        this.jogador2 = new Jogador("Maria");

//        int tamanhoInicialMao = 2;

        for(int i = 0; i < tamanhoInicialMao; i++){ // Otimização da lógica; ao invés de repetir a linha quatro vzs, usar um for
            this.jogador1.receberCarta(this.baralho.tirarCarta());
            this.jogador2.receberCarta(this.baralho.tirarCarta());
        }

//        this.jogador1.receberCarta(this.baralho.tirarCarta());
//        this.jogador1.receberCarta(this.baralho.tirarCarta());
//
//        this.jogador2.receberCarta(this.baralho.tirarCarta());
//        this.jogador2.receberCarta(this.baralho.tirarCarta());

    }

    public String toString(){

        String resultado = "Jogo de Baralho Genérico\n";
        resultado += "Jogadores:\n";
        resultado += "\n-" + this.jogador1.toString();
        resultado += "\n-" + this.jogador2.toString();
        return resultado;

    }
}
