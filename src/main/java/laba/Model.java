package laba;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
    private double Lamda;
    private int M;
    private double p;
    private ArrayList<Sub> users;
    private double T = 1_000_000;
    private ArrayList<Double> requests;
    private ArrayList<Double> Slout=new ArrayList<>();
    private ArrayList<Double> Sdelay= new ArrayList<>();
    private ArrayList<Double> SN = new ArrayList<>();

    public void init(double lamda, int m, double p) {
        Lamda = lamda;
        M = m;
        this.p = p;
        users = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public void Model(){
        InitUsers();
        double runTime = 0.0;
        double delay = 0.0;
        double N = 0.0;
        double lout=0.0;
        while (runTime<T){
            step(runTime);
            N += requests.size();
            if (requests.size()==1){
                delay += runTime - requests.get(0)+1.0;
                lout++;
                requests.remove(0);
            }
            requests.clear();
            runTime++;
        }
        Sdelay.add(delay/lout);
        SN.add(N/T);
        Slout.add(lout/(2*T));
        System.out.println("L: "+Lamda);
        System.out.println("Sdelay: "+delay/lout);
        System.out.println("SN: "+N/T);
        System.out.println("Slout: "+lout/(2*T));
        System.out.println("--------------------------");
    }

    private void InitUsers(){
        for(int i=0;i<M;i++){
            users.add(new Sub(Lamda/(double) M,T,p));
        }
    }

    private void step(double runTime){
        double req = 0.0;
        for(int i=0;i<M;i++){
            users.get(i).setBuffer(runTime);
            req = users.get(i).getElement(runTime);
            if(req!=0.0){
                requests.add(req);
            }
        }
    }

    public void vivod(int i){
        switch (i) {
            case 1:
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\79219\\IdeaProjects\\TMD_dopuski\\src\\main\\java\\laba\\Slout.csv"))){
                    Slout.forEach(o->{
                        try {
                            writer.write(o+"\n");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\79219\\IdeaProjects\\TMD_dopuski\\src\\main\\java\\laba\\SN.csv"))){
                    SN.forEach(o->{
                        try {
                            writer.write(o+"\n");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\79219\\IdeaProjects\\TMD_dopuski\\src\\main\\java\\laba\\Sdelay.csv"))){
                    Sdelay.forEach(o->{
                        try {
                            writer.write(o+"\n");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        System.out.println("end");
    }
}
