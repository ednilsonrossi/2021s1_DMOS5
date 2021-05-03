package br.edu.ifsp.arq.domos5_2021.sorteador_dmo.model;

import java.util.Random;

public class Sorteio {
    private static final int BORDER_DEFAULT = 100;
    private Random mRandom;
    private int mBorder;

    public Sorteio() {
        this.mRandom = new Random();
        this.mBorder = -1;
    }

    public Sorteio(int border) {
        this.mRandom = new Random();
        if(border > 1){
            mBorder = border;
        }else {
            mBorder = BORDER_DEFAULT;
        }
    }

    public int obtemInteiro(){
        if(mBorder == -1){
            return mRandom.nextInt();
        }else{
            return mRandom.nextInt(mBorder) + 1;
        }
    }

    public int getBorder() {
        return mBorder;
    }

    public static int novoInteiro(){
        Random random = new Random();
        return random.nextInt();
    }

    public static int novoInteiro(int maxBorder){
        return new Random().nextInt(maxBorder)+1;
    }

}
