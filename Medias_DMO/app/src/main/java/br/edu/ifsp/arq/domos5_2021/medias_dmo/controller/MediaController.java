package br.edu.ifsp.arq.domos5_2021.medias_dmo.controller;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaAritmetica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaHarmonica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaPonderada;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaStrategy;

public class MediaController {

    public static Double mediaPonderada(/*Indicar argumentos, se necessário*/){
        MediaPonderada ponderada = null;
        //TODO implementar o restante do método
        return media(ponderada);
    }

    public static Double mediaArimetica(/*Indicar argumentos, se necessário*/){
        MediaAritmetica aritmetica = null;
        //TODO implementar o restante do método
        return media(aritmetica);
    }

    public static Double mediaHarmonica(/*Indicar argumentos, se necessário*/){
        MediaHarmonica harmonica = null;
        //TODO implementar o restante do método
        return media(harmonica);
    }

    private static Double media(MediaStrategy mediaStrategy){
        return mediaStrategy.calcularMedia();
    }

}
