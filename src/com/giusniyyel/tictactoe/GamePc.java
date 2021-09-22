/*
 * TicTacToe
 * Version 1.0
 * Copyright (C) GiusNiyyel, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Daniel Campos <giusniyyel@gmail.com>, August 2019
 */
package com.giusniyyel.tictactoe;

import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jpl7.Query;

/**
 *
 * @author Admin
 */
public class GamePc extends javax.swing.JFrame {
    String[] tictactoe = {"0","0","0","0","0","0","0","0","0"};
    private String startGame = "x";
    private CleanArray clean = new CleanArray();
    
    private void prolog(){
        /*Prolog*/
        try{
            String conexion = "consult('minimax2.pl')";
            Query con = new Query(conexion);
            System.out.println(conexion + "" + (con.hasMoreSolutions() ? "ACEPTADO" : "FALLADO"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void back(){
        Home game = new Home();
        game.setVisible(true);
    }
    
    /*PONER FICHAS*/
    private void posicionate(){
        for(int i=0; i<9; i++){
            System.out.println(tictactoe[i]);
            if(tictactoe[i].equals("o")){
                System.out.println("Encontre O");
                setFichaO(i);
            }
        }
    }
    
    /*Coloca las fichas X*/
    private void setFichaX(int i){
        switch (i) {
            case 0:
                x1.setVisible(true);
                btn1.setText("x");
                break;
            case 1:
                x2.setVisible(true);
                btn2.setText("x");
                break;
            case 2:
                x3.setVisible(true);
                btn3.setText("x");
                break;
            case 3:
                x4.setVisible(true);
                btn4.setText("x");
                break;
            case 4:
                x5.setVisible(true);
                btn5.setText("x");
                break;
            case 5:
                x6.setVisible(true);
                btn6.setText("x");
                break;
            case 6:
                x7.setVisible(true);
                btn7.setText("x");
                break;
            case 7:
                x8.setVisible(true);
                btn8.setText("x");
                break;
            case 8:
                x9.setVisible(true);
                btn9.setText("x");
                break;
        }
    }
    
    /*Coloca las fichas O*/
    private void setFichaO(int i){
        switch (i) {
            case 0:
                o1.setVisible(true);
                btn1.setText("o");
                break;
            case 1:
                o2.setVisible(true);
                btn2.setText("o");
                break;
            case 2:
                o3.setVisible(true);
                btn3.setText("o");
                break;
            case 3:
                o4.setVisible(true);
                btn4.setText("o");
                break;
            case 4:
                o5.setVisible(true);
                btn5.setText("o");
                break;
            case 5:
                o6.setVisible(true);
                btn6.setText("o");
                break;
            case 6:
                o7.setVisible(true);
                btn7.setText("o");
                break;
            case 7:
                o8.setVisible(true);
                btn8.setText("o");
                break;
            case 8:
                o9.setVisible(true);
                btn9.setText("o");
                break;
        }
    }
    
    private void displayXO(){
        x1.setVisible(false);
        x2.setVisible(false);
        x3.setVisible(false);
        x4.setVisible(false);
        x5.setVisible(false);
        x6.setVisible(false);
        x7.setVisible(false);
        x8.setVisible(false);
        x9.setVisible(false);
        o1.setVisible(false);
        o2.setVisible(false);
        o3.setVisible(false);
        o4.setVisible(false);
        o5.setVisible(false);
        o6.setVisible(false);
        o7.setVisible(false);
        o8.setVisible(false);
        o9.setVisible(false);
    }
    
    private void cpuMove(){
        String move = "start_it([o," + Arrays.toString(tictactoe) + ",_],o,NewBoard).";
            Query exec = new Query(move);
            if (exec.hasSolution()) {
                String x = exec.oneSolution().get("NewBoard").toString();
                tictactoe = clean.clean(x);
                posicionate();
            }
    }
    
    public void transparency(){
        btnClose5.setOpaque(false);
        btnClose5.setContentAreaFilled(false);
        btnClose5.setBorderPainted(false);
    }
    
    private void winning(){
        int cm=0;
        /*WIN PROLOG*/
            try{
                /*O*/
                String winnableo = "win(" +Arrays.toString(tictactoe)+", o).";
                Query wino = new Query(winnableo);
                if(wino.hasSolution()){
                    cm=1;
                    JOptionPane.showMessageDialog(null, "Ha ganado el PC","Tic-Tac-Toe - Ganador",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    back();
                }
                
                /*X*/
                String winnablex = "win(" +Arrays.toString(tictactoe)+", x).";
                Query win = new Query(winnablex);
                if(win.hasSolution()){
                    cm=1;
                    JOptionPane.showMessageDialog(null, "Has ganado Jugador 1","Tic-Tac-Toe - Ganador",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    back();
                }
                
                /*Gato*/
                if(cm==0){
                    if(btn1.getText()!=""&&btn2.getText()!=""&&btn3.getText()!=""&&btn4.getText()!=""&&btn5.getText()!=""&&btn6.getText()!=""&&btn7.getText()!=""&&btn8.getText()!=""&&btn9.getText()!=""){
                        JOptionPane.showMessageDialog(null, "Ha ganado el gato","Tic-Tac-Toe - Ganador",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        back();
                    }
                }
            } catch (Exception e){
                    e.printStackTrace();
            }
    }

    /**
     * Creates new form Game
     */
    public GamePc() {
        initComponents();
        displayXO();
        transparency();
        prolog();
        /* NOMBRE QUE MUESTRA */
        setTitle("Nuevo Juego - Tic-Tac-Toe");
        /* ICONO DEL SOFTWARE */
        setIconImage(new ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/icon.png")).getImage());
        /* REGLAS OTORGADAS AL JFRAME */
        setResizable(false);
        /* Fondo */
        this.setSize(1024,600);
        /*Null*/
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        x9 = new javax.swing.JLabel();
        x8 = new javax.swing.JLabel();
        x7 = new javax.swing.JLabel();
        x6 = new javax.swing.JLabel();
        x5 = new javax.swing.JLabel();
        x4 = new javax.swing.JLabel();
        x3 = new javax.swing.JLabel();
        x2 = new javax.swing.JLabel();
        x1 = new javax.swing.JLabel();
        o9 = new javax.swing.JLabel();
        o8 = new javax.swing.JLabel();
        o7 = new javax.swing.JLabel();
        o6 = new javax.swing.JLabel();
        o5 = new javax.swing.JLabel();
        o4 = new javax.swing.JLabel();
        o3 = new javax.swing.JLabel();
        o2 = new javax.swing.JLabel();
        o1 = new javax.swing.JLabel();
        btnClose5 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        x9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x9);
        x9.setBounds(580, 430, 100, 100);

        x8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x8);
        x8.setBounds(460, 430, 100, 100);

        x7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x7);
        x7.setBounds(340, 430, 100, 100);

        x6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x6);
        x6.setBounds(580, 310, 100, 100);

        x5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x5);
        x5.setBounds(460, 310, 100, 100);

        x4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x4);
        x4.setBounds(340, 310, 100, 100);

        x3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x3);
        x3.setBounds(580, 190, 100, 100);

        x2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x2);
        x2.setBounds(460, 190, 100, 100);

        x1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/X1.png"))); // NOI18N
        getContentPane().add(x1);
        x1.setBounds(340, 190, 100, 100);

        o9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o9);
        o9.setBounds(580, 430, 100, 100);

        o8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o8);
        o8.setBounds(460, 430, 100, 100);

        o7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o7);
        o7.setBounds(340, 430, 100, 100);

        o6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o6);
        o6.setBounds(580, 310, 100, 100);

        o5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o5);
        o5.setBounds(460, 310, 100, 100);

        o4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o4);
        o4.setBounds(340, 310, 100, 100);

        o3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o3);
        o3.setBounds(580, 190, 100, 100);

        o2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o2);
        o2.setBounds(460, 190, 100, 100);

        o1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/O1.png"))); // NOI18N
        getContentPane().add(o1);
        o1.setBounds(340, 190, 100, 100);

        btnClose5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/close.png"))); // NOI18N
        btnClose5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose5ActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose5);
        btnClose5.setBounds(980, 10, 35, 35);

        btn1.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn1);
        btn1.setBounds(340, 190, 100, 100);

        btn2.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn2);
        btn2.setBounds(460, 190, 100, 100);

        btn3.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn3);
        btn3.setBounds(580, 190, 100, 100);

        btn4.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        getContentPane().add(btn4);
        btn4.setBounds(340, 310, 100, 100);

        btn5.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        getContentPane().add(btn5);
        btn5.setBounds(460, 310, 100, 100);

        btn6.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        getContentPane().add(btn6);
        btn6.setBounds(580, 310, 100, 100);

        btn8.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        getContentPane().add(btn8);
        btn8.setBounds(460, 430, 100, 100);

        btn9.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        getContentPane().add(btn9);
        btn9.setBounds(580, 430, 100, 100);

        btn7.setFont(new java.awt.Font("TeXGyreAdventor", 0, 3)); // NOI18N
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        getContentPane().add(btn7);
        btn7.setBounds(340, 430, 100, 100);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(330, 180, 360, 360);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/giusniyyel/tictactoe/src/BGGatopc.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1024, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        /*Prolog*/      
        if(btn1.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn1.getText()==""){
            tictactoe[0] = startGame;
            setFichaX(0);
            cpuMove();
            winning();
        }  
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        /*Prolog*/        
        if(btn2.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn2.getText()==""){
            tictactoe[1] = startGame;
            setFichaX(1);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        /*Prolog*/ 
        if(btn3.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn3.getText()==""){
            tictactoe[2] = startGame;
            setFichaX(2);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        /*Prolog*/
        if(btn4.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn4.getText()==""){
            tictactoe[3] = startGame;
            setFichaX(3);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        /*Prolog*/
        if(btn5.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn5.getText()==""){
            tictactoe[4] = startGame;
            setFichaX(4);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        /*Prolog*/       
        if(btn6.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn6.getText()==""){
            tictactoe[5] = startGame;
            setFichaX(5);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        /*Prolog*/        
        if(btn7.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn7.getText()==""){
            tictactoe[6] = startGame;
            setFichaX(6);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        /*Prolog*/        
        if(btn8.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn8.getText()==""){
            tictactoe[7] = startGame;
            setFichaX(7);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        /*Prolog*/        
        if(btn9.getText()!=""){
            JOptionPane.showMessageDialog(null, "¡Esta casilla está ocupada, busca otra","Tic-Tac-Toe - Ayuda",JOptionPane.ERROR_MESSAGE);
        }
        if(btn9.getText()==""){
            tictactoe[8] = startGame;
            setFichaX(8);
            cpuMove();
            winning();
        }
    }//GEN-LAST:event_btn9ActionPerformed

    private void btnClose5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose5ActionPerformed
        dispose();
        back();
    }//GEN-LAST:event_btnClose5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GamePc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamePc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamePc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamePc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnClose5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel o1;
    private javax.swing.JLabel o2;
    private javax.swing.JLabel o3;
    private javax.swing.JLabel o4;
    private javax.swing.JLabel o5;
    private javax.swing.JLabel o6;
    private javax.swing.JLabel o7;
    private javax.swing.JLabel o8;
    private javax.swing.JLabel o9;
    private javax.swing.JLabel x1;
    private javax.swing.JLabel x2;
    private javax.swing.JLabel x3;
    private javax.swing.JLabel x4;
    private javax.swing.JLabel x5;
    private javax.swing.JLabel x6;
    private javax.swing.JLabel x7;
    private javax.swing.JLabel x8;
    private javax.swing.JLabel x9;
    // End of variables declaration//GEN-END:variables
}
