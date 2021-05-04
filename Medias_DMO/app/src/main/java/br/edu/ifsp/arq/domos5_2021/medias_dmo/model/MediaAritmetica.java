package br.edu.ifsp.arq.domos5_2021.medias_dmo.model;

import java.util.Arrays;
import java.util.List;

public class MediaAritmetica implements MediaStrategy{

    private List<Double> values;

    public MediaAritmetica(Double... doubles) {
        if(doubles != null && doubles.length > 0){
            values = Arrays.asList(doubles);
        }
    }

    @Override
    public Double calcularMedia() {
        Double sum = 0D;
        for(Double d : values){
            sum += d;
        }
        return sum/values.size();
    }
}
