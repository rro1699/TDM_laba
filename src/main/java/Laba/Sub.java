package Laba;

import java.util.ArrayList;

public class Sub {
    private double p;
    private int T;
    private ArrayList<Double> potok;
    private double buffer;
    private int pointer;

    public Sub(double lamda,double p, int t) {
        this.p = p;
        T = t;
        potok = new ArrayList<>();
        potok.add(-(Math.log(Math.random()) / lamda));
        while(potok.get(potok.size()-1)<(T*2)){
            potok.add(potok.get(potok.size()-1) + (-Math.log(Math.random()) / lamda));
        }
        pointer= 0;
        buffer = 0.0;
    }


    public double getElement(double runTime){
        double result=0.0;
        if(buffer!=0.0 && buffer<runTime){
            double prob = Math.random();
            if(prob<p){
                result = buffer;
                buffer = 0.0;
            }
        }
        return result;
    }

    public  void setBuffer(double runTime){
        while(potok.get(pointer)<runTime){
            if(buffer==0.0){
                buffer  =potok.get(pointer)+1.0;
            }
            pointer++;
        }
    }
}
