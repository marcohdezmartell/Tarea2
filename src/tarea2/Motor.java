/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

/**
 *
 * @author sonic
 */
public class Motor {
    private Cazador cazador;
    private Objetivo objetivo;
    private Objetivo[] reflexionObj;
    private Cazador[] reflexionCaz;
    private String historial[];
    private int tiempo;
    private static final int tLimite = 5000;
    public Motor() {
        reflexionObj = new Objetivo[9];
        reflexionCaz = new Cazador[9];
        cazador = new Cazador(Tools.aleatorio(), Tools.aleatorio());
        objetivo = new Objetivo(Tools.aleatorio(), Tools.aleatorio());
        historial = new String[5000];
        tiempo = 0;
    }
    
    public String[] jugar(){
        Objetivo mejorReflex;
        Cazador mejorCaz;
        
        for (tiempo = 0; tiempo < 5000; tiempo++) {
            
        calcularPlanos(objetivo, cazador);
        guardarHist();
        if(fueCapturado()){
            objetivo.mover(Tools.aleatorio(), Tools.aleatorio());
        }else{
            mejorReflex = cazador.analizar(reflexionObj);
            mejorCaz = objetivo.analizar(reflexionCaz);
            Figura nuevasCooCaz = cazador.calcular(mejorReflex);
            Figura nuevasCooObj = objetivo.calcular(mejorCaz);
            mover(nuevasCooCaz, nuevasCooObj);
        }
        }
        
        return historial;
    }
    private boolean fueCapturado(){
        if(cazador.x == objetivo.x){
            if(cazador.y == objetivo.y){
                return true;
            }
        }
    return false;
    }
    private void guardarHist(){
        historial[tiempo] = "" + cazador + ", "+objetivo;
    }
    
    private void calcularPlanos(Objetivo objetivo, Cazador cazador1){
        //1 centra
        reflexionObj[0]=objetivo;
        //2 arriba izq
        reflexionObj[1]= new Objetivo(objetivo.x-10000, objetivo.y+10000);
        //3 arriba
        reflexionObj[2]= new Objetivo(objetivo.x, objetivo.y+10000);
        //4 arriba der
        reflexionObj[3]= new Objetivo(objetivo.x+10000, objetivo.y+10000);
        //5 izquierda
        reflexionObj[4]=new Objetivo(objetivo.x-10000, objetivo.y);
        //6 derecha
        reflexionObj[5]=new Objetivo(objetivo.x+10000, objetivo.y);
        //7 izq abajo
        reflexionObj[6]=new Objetivo(objetivo.x-10000, objetivo.y-10000);
        //8 abajo
        reflexionObj[7]=new Objetivo(objetivo.x, objetivo.y-10000);
        //9 der abajo
        reflexionObj[8]=new Objetivo(objetivo.x+10000, objetivo.y-10000);
        //1 centra
        reflexionCaz[0]=cazador1;
        //2 arriba izq
        reflexionCaz[1]= new Cazador(cazador1.x-10000, cazador1.y+10000);
        //3 arriba
        reflexionCaz[2]= new Cazador(cazador1.x, cazador1.y+10000);
        //4 arriba der
        reflexionCaz[3]= new Cazador(cazador1.x+10000, cazador1.y+10000);
        //5 izquierda
        reflexionCaz[4]=new Cazador(cazador1.x-10000, cazador1.y);
        //6 derecha
        reflexionCaz[5]=new Cazador(cazador1.x+10000, cazador1.y);
        //7 izq abajo
        reflexionCaz[6]=new Cazador(cazador1.x-10000, cazador1.y-10000);
        //8 abajo
        reflexionCaz[7]=new Cazador(cazador1.x, cazador1.y-10000);
        //9 der abajo
        reflexionCaz[8]=new Cazador(cazador1.x+10000, cazador1.y-10000);
    }
    private void mover(Figura coordenadas, Figura coordenadasObj){
        if(coordenadas.x<0){
            coordenadas.x = 10000+coordenadas.x;
        }else{
            if(coordenadas.x>10000){
            coordenadas.x = coordenadas.x-10000;
            }
        }
        if(coordenadas.y<0){
            coordenadas.y = 10000+coordenadas.y;
        }else{
            if(coordenadas.y>10000){
            coordenadas.y = coordenadas.y-10000;
            }
        }
        cazador.x = coordenadas.x;
        cazador.y = coordenadas.y;
        if(coordenadasObj.x<0){
            coordenadasObj.x = 10000+coordenadasObj.x;
        }else{
            if(coordenadasObj.x>10000){
            coordenadasObj.x = coordenadasObj.x-10000;
            }
        }
        if(coordenadasObj.y<0){
            coordenadasObj.y = 10000+coordenadasObj.y;
        }else{
            if(coordenadasObj.y>10000){
            coordenadasObj.y = coordenadasObj.y-10000;
            }
        }
        objetivo.x = coordenadasObj.x;
        objetivo.y = coordenadasObj.y;
    }
}
