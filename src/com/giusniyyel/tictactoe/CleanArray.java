/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.giusniyyel.tictactoe;

/**
 *
 * @author Admin
 */
public class CleanArray {
    
    public String[] clean(String arrayc){
        String [] tictactoe = new String[9];
        
        /*Obtiene los valores en las posiciones*/
        String x = arrayc.substring(6,7);
        String y = arrayc.substring(15,16);
        String w = arrayc.substring(24,25);
        String z = arrayc.substring(33,34);
        String c = arrayc.substring(42,43);
        String v = arrayc.substring(51,52);
        String b = arrayc.substring(60,61);
        String n = arrayc.substring(69,70);
        String m = arrayc.substring(78,79);

        /*asigna los valores en las posiciones del vector*/
        tictactoe[0]=x;
        tictactoe[1]=y;
        tictactoe[2]=w;
        tictactoe[3]=z;
        tictactoe[4]=c;
        tictactoe[5]=v;
        tictactoe[6]=b;
        tictactoe[7]=n;
        tictactoe[8]=m;
        
        /*retorna el vector ya limpio*/
        return tictactoe;
    }    
}
