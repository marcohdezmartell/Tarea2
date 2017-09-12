/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.awt.Color;

/**
 *
 * @author sonic
 */
public class Objetivo extends Figura{
    private static final int unidad = 50;
    public Objetivo(int x, int y) {
        super(x, y, Color.GREEN);
    }
    
    public Cazador analizar(Cazador reflexion[]){
        double mejor = Double.MAX_VALUE;
        double distancia = 0.0;
        Cazador mejorObj = new Cazador(0, 0);
        for (int i = 0; i < reflexion.length; i++) {
            distancia = calcuDistancia(this.x, this.y, reflexion[i].x, reflexion[i].y);
            if(distancia<mejor){
                mejor = distancia;
                mejorObj = reflexion[i];
            }
        }
        return mejorObj;
    }
    
    public Figura calcular(Cazador cazador){
        
        int pendiente = 0;
        Figura coordenadas=null;
        try{
            pendiente = Tools.redondear((this.y - cazador.y)/(this.x-cazador.x));
        }catch(Exception e){
            pendiente = 0;
        }
        if(cazador.y>this.y){
            if(pendiente!=0){
                coordenadas=new Figura(Tools.redondear((-unidad /pendiente)+this.x), this.y-unidad, null);
            }else{
                coordenadas=new Figura(this.x, this.y-unidad,null);
            }
        }else{if(cazador.y<this.y){
            if(pendiente!=0){
                coordenadas=new Figura(Tools.redondear((unidad /pendiente)+this.x), this.y+unidad, null);
            }else{
                coordenadas=new Figura(this.x, this.y+unidad,null);
            }
            }
        }
        int diferencia = unidad+1;
        
        if(coordenadas!=null){
            diferencia = coordenadas.x - this.x;
        }if(Math.abs(this.y-cazador.y)<=100){
            diferencia = unidad+1;
        }
        if(Math.abs(diferencia)>unidad){
            if(cazador.x>this.x){
                coordenadas = new Figura(this.x-unidad, Tools.redondear((pendiente*(-unidad)) + this.y), null);
            }else{
                coordenadas = new Figura(this.x+unidad, Tools.redondear((pendiente*unidad) + this.y), null);
                
            }
        }
        return coordenadas;
    }
    
    private double calcuDistancia(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1 - x2, 2)+Math.pow(y1 - y2, 2));
    }
}
