package laba;

import java.util.ArrayList;

public class Sub {
    private ArrayList<Double> potok;
    private double buffer;
    private int pointer;
    private double p;

    public Sub(double lamda, double T,double p) {
        //System.out.println(lamda);
        potok = new ArrayList<>();
        potok.add(-(Math.log(Math.random()) / lamda));
        while(potok.get(potok.size()-1)<(T*2)){
            potok.add(potok.get(potok.size()-1) + (-Math.log(Math.random()) / lamda));
        }
        pointer=0;
        this.p=p;
    }

    public double getElement(double runTime){
        double res = 0.0;
        if(buffer<runTime){
            double prob = Math.random();
            if(prob<p) {
                res = buffer;
                buffer = 0.0;
            }
        }
        return res;
    }

    public void setBuffer(double runTime){
        while(potok.get(pointer)<runTime){
            if(buffer==0.0){
                buffer=potok.get(pointer)+1.0;
            }
            pointer++;
        }
    }

}
