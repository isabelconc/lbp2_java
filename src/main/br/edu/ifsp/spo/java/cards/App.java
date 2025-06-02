package br.edu.ifsp.spo.java.cards;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.itens.Naipe;
import br.edu.ifsp.spo.java.cards.itens.Valor;
import br.edu.ifsp.spo.java.cards.nucleo.Jogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class App {

    public static void main(String[] args){
//        for (var naipe : Naipe.values()){
//            for (var valor : Valor.values()){
//                mostrarCartas(naipe, valor);
//            }
//        }

//        var carta = new Carta(Naipe.COPAS, Valor.AS);

        var cartas = new ArrayList<Carta>(Arrays.asList());
            for (var naipe : Naipe.values()) {
                for (var valor : Valor.values()) {
                    cartas.add(new Carta(naipe, valor));
                }
            };


//    private static void mostrarCartas(Naipe naipe, Valor valor) {
//        var carta = new Carta(naipe, valor);

        var linhaAtual = -1;
//      var linhas = new String[8];

        var cardOffset = "  ";

        int cartasPorLinha = 5;
        for (int i = 0; i < cartas.size(); i += cartasPorLinha) {
            var linhas = new String[8];
            for (int k = 0; k < linhas.length; k++) { // Começando vazio para evitar null
                linhas[k] = "";
            }

            for (int j = i; j < i + cartasPorLinha && j < cartas.size(); j++) {
                var carta = cartas.get(j);

                var caminhoCompleto = String.format(
                    "%s/%s/%s.txt",
                    "/cards",
                    carta.getNaipe().toString().toLowerCase(),
                    carta.getValor().toString().toLowerCase());

            System.out.println(caminhoCompleto);

    //        InputStream inputStream = null;

            try (BufferedReader reader = new BufferedReader( // bufferedreader não recebe tipo string
                    new InputStreamReader(App.class.getResourceAsStream(caminhoCompleto))) ){
    //            InputStream inputStream = App.class.getResourceAsStream(caminhoCompleto)// não dispara IOExeception pois a estrutura try catch tenta minimizar as execeptions (custos), e então como não pôde achar o arquivo, dispara como nullpointer

                String line;

                while ((line = reader.readLine()) != null){
    //                Thread.sleep(100); // sleep interrompe a linha de execução (classe Thread) após 1000 milisegundos
                    linhaAtual++;

                    if(linhas[linhaAtual] == null){
                        linhas[linhaAtual] = line;
                    } else {
                        linhas[linhaAtual] = linhas[linhaAtual] + cardOffset + line;
                    }
                }
                linhaAtual = -1;


    //            if(inputStream == null) {
    //                System.out.println("O arquivo não existe!");
    //
    //            }else{
    //
    //                byte[] buffer = new byte[inputStream.available()];
    //                inputStream.read(buffer);
    //
    //                var cartaComoString = new String(buffer);
    //                System.out.println(cartaComoString);
    //            }

            } catch (IOException exception){
                System.out.println("Exception: " + exception.getMessage() + exception.getClass().getName());

            } catch (Exception exception){
                System.out.println("Exception: " + exception.getMessage() + exception.getClass().getName());
            }

        }
        for(var linha : linhas){
            System.out.println(linha);
        }

        }
    }
}