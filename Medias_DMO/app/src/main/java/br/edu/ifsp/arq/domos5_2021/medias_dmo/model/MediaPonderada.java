package br.edu.ifsp.arq.domos5_2021.medias_dmo.model;

public class MediaPonderada implements MediaStrategy{

    private Double value1;
    private Double value2;
    private Double value3;
    private Double value4;
    private Double value5;
    private int weight_value1;
    private int weight_value2;
    private int weight_value3;
    private int weight_value4;
    private int weight_value5;

    public MediaPonderada(Double value1, Double value2, Double value3, Double value4, Double value5, int weight_value1, int weight_value2, int weight_value3, int weight_value4, int weight_value5) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.weight_value1 = weight_value1;
        this.weight_value2 = weight_value2;
        this.weight_value3 = weight_value3;
        this.weight_value4 = weight_value4;
        this.weight_value5 = weight_value5;
    }

    @Override
    public Double calcularMedia() {
        //TODO implementar método
        return null;
    }
}
