package br.edu.ifsp.spo.java.cards;

import java.util.ArrayList;
import java.util.List;

public class Jogador {

    private final String nome;
    private final List<Carta> mao;

    public Jogador(String nome){
        this.mao = new ArrayList<>(); // ArrayList<>() é igual a ArrayList<Carta>()
        this.nome = nome;

    }

    public void receberCarta(Carta carta){
        this.mao.add(carta);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        String resultado = "Jogador:" + getNome();
        resultado += "\nA mão do jogador é:";

        for (Carta carta : this.mao){
            resultado += "\n-" + carta.toString();
        }
        return resultado;
    }
}
