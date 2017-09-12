/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author sonic
 */
public class Cazador extends Figura{
    
    public Cazador(int x, int y) {
        super(x, y, Color.CYAN);
    }
    public Objetivo analizar(Objetivo reflexion[]){
        double mejor = Double.MAX_VALUE;
        double distancia = 0.0;
        Objetivo mejorObj = new Objetivo(0, 0);
        for (int i = 0; i < reflexion.length; i++) {
            distancia = calcuDistancia(this.x, this.y, reflexion[i].x, reflexion[i].y);
            if(distancia<mejor){
                mejor = distancia;
                mejorObj = reflexion[i];
            }
        }
        return mejorObj;
    }
    
    public Figura calcular(Objetivo presa){ 
        int unidady = 100;
        int unidadx = 100;
        if(Math.abs(presa.x-this.x)<=unidadx/2){
            if(Math.abs(presa.y-this.y)<=unidady/2){
                unidady = Math.abs(Math.abs(presa.y)+50);
                unidadx = Math.abs(Math.abs(presa.x)+50);
            }
        }
        int pendiente = 0;
        Figura coordenadas=null;
        try{
            pendiente = Tools.redondear((this.y - presa.y)/(this.x-presa.x));
        }catch(Exception e){
            pendiente = 0;
        }
        if(presa.y>this.y){
            if(pendiente!=0){
                coordenadas=new Figura(Tools.redondear((unidady /pendiente)+this.x), this.y+unidady, null);
            }else{
                coordenadas=new Figura(this.x, this.y+unidady,null);
            }
        }else{if(presa.y<this.y){
            if(pendiente!=0){
                coordenadas=new Figura(Tools.redondear((-unidady /pendiente)+this.x), this.y-unidady, null);
            }else{
                coordenadas=new Figura(this.x, this.y-unidady,null);
            }
            }
        }
        int diferencia = 100+1;
        
        if(coordenadas!=null){
            diferencia = coordenadas.x - this.x;
        }if(Math.abs(this.y-presa.y)<=100){
            diferencia = 100+1;
        }
        if(Math.abs(diferencia)>100){
            if(presa.x>this.x){
                coordenadas = new Figura(this.x+unidadx, Tools.redondear((pendiente*unidadx) + this.y), null);
            }else{
                coordenadas = new Figura(this.x-unidadx, Tools.redondear((pendiente*(-unidadx)) + this.y), null);
            }
        }
        return coordenadas;
    }
    
    private double calcuDistancia(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1 - x2, 2)+Math.pow(y1 - y2, 2));
    }
    
}
