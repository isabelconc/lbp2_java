package br.edu.ifsp.spo.java.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private final List<Carta> cartas;

    public Baralho() {
        this.cartas = new ArrayList<>(); // inicialização da lista cartas para vazia

        for (Naipe suit : Naipe.values()) {
            for (Valor rank : Valor.values()) {
                this.cartas.add(new Carta(suit, rank));
            }
        } // define e guarda as cartas

        Collections.shuffle(this.cartas);
    }

    public Carta tirarCarta() {

        Carta cartaASerDada = this.cartas.remove(0);

        return cartaASerDada;
    }

    public int cartasRestantes() {
        return this.cartas.size();
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cartasRestantes() +
                '}';
    }

    public List<Carta> getCards() {
        return cartas;
    }
}
