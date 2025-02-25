/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package blackjack;

import Menu.Menu;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author aazur
 */
public class BlackJack extends javax.swing.JFrame {
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static int dinerousuario = 0;

    private List<String[]> cartasJugadorValores = new ArrayList<>(); // Guarda el nombre y la imagen de la carta
    private List<String[]> cartasDealerValores = new ArrayList<>(); // Guarda el nombre y la imagen de cada carta

    private List<JLabel> cartasJugador = new ArrayList<>();
    private List<JLabel> cartasDealer = new ArrayList<>();
    private int contases = 0;

    private static Baraja baraja = new Baraja();
    private static Scanner sc = new Scanner(System.in);

    enum Apostar {
        cinco,
        diez,
        veinticinco,
        cincuenta,
        setentaycinco,
        cien,
        cero

    }
    Apostar apostador = Apostar.cero;

    /**
     * Creates new form WhiteJack
     */
    public BlackJack() {
        initComponents();
        
    }

    public BlackJack(int dinero) {
        dinerousuario = dinero;
        initComponents();
    }

    public boolean verificarBlackJack(int sumajugador, int sumadealer, ImageIcon imagen) {
        if (sumajugador == 21 && sumadealer != 21) {
            D2.setIcon(imagen);
            JOptionPane.showMessageDialog(this, "GANASTE!");
            return true;
        }

        if (sumajugador == 21 && sumadealer == 21) {
            D2.setIcon(imagen);
            JOptionPane.showMessageDialog(this, "EMPATASTE!");
            return true;
        }

        return false;
    }

    public int turnoJugador(int sumajugador) {
        String[] cartaElegida = baraja.robarCarta();
        ImageIcon imagenCarta = new ImageIcon(getClass().getResource(cartaElegida[1]));

        JLabel nuevaCarta = new JLabel();
        nuevaCarta.setIcon(imagenCarta);

        cartasJugador.add(nuevaCarta);
        cartasJugadorValores.add(cartaElegida);

        cartasjugador.add(nuevaCarta);
        cartasjugador.revalidate();
        cartasjugador.repaint();

        sumajugador += comprobarNumero(cartaElegida[0], sumajugador);
        sumajugador = ajustarAses(sumajugador, cartasJugadorValores);

        return sumajugador;
    }

    public int turnoDealer(int sumadealer, int sumajugador) {
        while (sumadealer < 17 || (sumadealer < sumajugador && sumadealer <= 21)) {
            String[] cartaElegida = baraja.robarCarta();
            ImageIcon imagenCarta = new ImageIcon(getClass().getResource(cartaElegida[1]));

            JLabel nuevaCarta = new JLabel();
            nuevaCarta.setIcon(imagenCarta);

            cartasdealer.add(nuevaCarta);  // Agregar la carta al panel del dealer
            cartasdealer.revalidate();
            cartasdealer.repaint();

            cartasDealer.add(nuevaCarta);
            cartasDealerValores.add(cartaElegida);

            sumadealer += comprobarNumero(cartaElegida[0], sumadealer);
            sumadealer = ajustarAses(sumadealer, cartasDealerValores);
            System.out.println(sumadealer);
        }

        return sumadealer;
    }

    private int ajustarAses(int suma, List<String[]> cartas) {
        for (String[] carta : cartas) {
            if (carta[0].equals("1") && suma > 21) {
                suma -= 10; // Convierte As de 11 a 1
            }
        }
        return suma;
    }

    public void determinarGanador(int sumajugador, int sumadealer) {
        if (sumadealer > 21) {
            JOptionPane.showMessageDialog(this, "El dealer se pasó ganaste");
        } else if (sumadealer == sumajugador) {
            JOptionPane.showMessageDialog(this, "Empate");
        } else if (sumadealer > sumajugador) {
            JOptionPane.showMessageDialog(this, "El dealer ganó");
        } else {
            JOptionPane.showMessageDialog(this, "Ganaste");
        }
    }

    public int comprobarNumero(String numero, int suma) {

        if (numero.matches(".*(2|3|4|5|6|7|8|9|10).*")) {
            return Integer.parseInt(numero.replaceAll("\\D", ""));
        }
        if (numero.equals("1")) {
            if (contases < 1) {
                return 1;
            }
            contases++;
            return (suma + 11 > 21) ? 1 : 11;
        }
        return 10; // J, Q, K valen 10
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        todo = new javax.swing.JPanel();
        mazo = new javax.swing.JLabel();
        jugar = new javax.swing.JButton();
        monedero = new javax.swing.JButton();
        cartasdealer = new javax.swing.JPanel();
        D2 = new javax.swing.JLabel();
        D1 = new javax.swing.JLabel();
        cartasjugador = new javax.swing.JPanel();
        J1 = new javax.swing.JLabel();
        J2 = new javax.swing.JLabel();
        cinco = new javax.swing.JLabel();
        diez = new javax.swing.JLabel();
        veinticinco = new javax.swing.JLabel();
        setentaycinco = new javax.swing.JLabel();
        cien = new javax.swing.JLabel();
        cincuenta = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        retroceder = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        todo.setBackground(new java.awt.Color(0, 204, 0));

        mazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N

        jugar.setText("Empezar a jugar");
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });

        monedero.setBackground(new java.awt.Color(0, 204, 0));
        monedero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/fichas-de-casino (1) (1).png"))); // NOI18N
        monedero.setBorderPainted(false);
        monedero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monederoActionPerformed(evt);
            }
        });

        cartasdealer.setBackground(new java.awt.Color(0, 204, 0));
        cartasdealer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        D2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        cartasdealer.add(D2);

        D1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        cartasdealer.add(D1);

        cartasjugador.setBackground(new java.awt.Color(0, 204, 0));
        cartasjugador.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        J1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        cartasjugador.add(J1);

        J2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        cartasjugador.add(J2);

        cinco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/5.png"))); // NOI18N
        cinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cincoMouseClicked(evt);
            }
        });

        diez.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/10.png"))); // NOI18N
        diez.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diezMouseClicked(evt);
            }
        });

        veinticinco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/25.png"))); // NOI18N
        veinticinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                veinticincoMouseClicked(evt);
            }
        });

        setentaycinco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/75.png"))); // NOI18N
        setentaycinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setentaycincoMouseClicked(evt);
            }
        });

        cien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/100.png"))); // NOI18N
        cien.setToolTipText("");
        cien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cienMouseClicked(evt);
            }
        });

        cincuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/pngegg (1).png"))); // NOI18N
        cincuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cincuentaMouseClicked(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("BLACKJACK");

        retroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dados/return.png"))); // NOI18N
        retroceder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retrocederMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout todoLayout = new javax.swing.GroupLayout(todo);
        todo.setLayout(todoLayout);
        todoLayout.setHorizontalGroup(
            todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, todoLayout.createSequentialGroup()
                .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(todoLayout.createSequentialGroup()
                        .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(todoLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(monedero))
                            .addGroup(todoLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(mazo)))
                        .addGap(75, 75, 75)
                        .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cartasdealer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cartasjugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(todoLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jugar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cinco)
                        .addComponent(diez)
                        .addComponent(veinticinco)
                        .addComponent(cien))
                    .addComponent(setentaycinco, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cincuenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addGroup(todoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder)
                .addGap(229, 229, 229)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        todoLayout.setVerticalGroup(
            todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(retroceder)
                    .addComponent(titulo))
                .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(todoLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cartasdealer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(todoLayout.createSequentialGroup()
                                .addComponent(cinco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(diez)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(veinticinco)))
                        .addGroup(todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(todoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cincuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(setentaycinco)
                                .addGap(18, 18, 18)
                                .addComponent(cien))
                            .addGroup(todoLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jugar)
                                .addGap(80, 80, 80)
                                .addComponent(cartasjugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(172, Short.MAX_VALUE))
                    .addGroup(todoLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(mazo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(monedero)
                        .addGap(51, 51, 51))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(todo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(todo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        // TODO add your handling code here:

        for (JLabel carta : cartasJugador) {
            cartasjugador.remove(carta);
        }
        for (JLabel carta : cartasDealer) {
            cartasdealer.remove(carta);
        }
        cartasJugador.clear(); // Limpiar la lista
        ImageIcon icon = new ImageIcon("Different-BikeBack.png");
        J1.setIcon(icon);
        J2.setIcon(icon);
        D1.setIcon(icon);
        D2.setIcon(icon);

        baraja.reiniciarBaraja();
        contases = 0;

        int sumajugador = 0;
        JLabel[] etiquetasCartas = {J1, J2};
        for (int i = 0; i < 2; i++) {               //CARTAS INICIALES JUGADOR
            String[] cartaElegida = baraja.robarCarta();
            sumajugador += comprobarNumero(cartaElegida[0], sumajugador);
            // Cargar la imagen en un ImageIcon
            ImageIcon imagenCarta = new ImageIcon(getClass().getResource(cartaElegida[1]));
            // Asignar la imagen a la etiqueta correspondiente
            etiquetasCartas[i].setIcon(imagenCarta);
        }

        int sumadealer = 0;         //CARTAS INICIALES DEALER
        JLabel[] etiquetasCartasDealer = {D1, D2};
        ImageIcon cartasinrevelar = new ImageIcon();
        for (int i = 0; i < 2; i++) {
            String[] cartaElegida = baraja.robarCarta();
            sumadealer += comprobarNumero(cartaElegida[0], sumadealer);
            ImageIcon imagenCarta = new ImageIcon(getClass().getResource(cartaElegida[1]));
            if (i == 0) {
                etiquetasCartasDealer[i].setIcon(imagenCarta);
            } else {
                //Guardo la carta sin revelar del dealer
                etiquetasCartasDealer[i].setIcon(icon);
                cartasinrevelar = new ImageIcon(getClass().getResource(cartaElegida[1]));
            }

        }
        System.out.println("Jugador: suma = " + sumajugador);
        System.out.println("Dealer: suma = " + sumadealer);
        if (verificarBlackJack(sumajugador, sumadealer, cartasinrevelar)) {
            return;
        }

        do {
            int respuesta = JOptionPane.showConfirmDialog(this, "Robar", "¿Quieres robar?", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {

                sumajugador = turnoJugador(sumajugador);
                System.out.println(sumajugador);

            } else {
                break;
            }
        } while (sumajugador < 21);

        D2.setIcon(cartasinrevelar);
        if (sumajugador > 21) {
            JOptionPane.showMessageDialog(this, "PERDISTE!");
            return;
        }
        if (verificarBlackJack(sumajugador, sumadealer, cartasinrevelar)) {
            return;
        }

        sumadealer = turnoDealer(sumadealer, sumajugador);

        determinarGanador(sumajugador, sumadealer);
        return;
    }//GEN-LAST:event_jugarActionPerformed

    private void monederoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monederoActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Usted tiene $" + dinerousuario);
    }//GEN-LAST:event_monederoActionPerformed

    private void cincoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cincoMouseClicked
        // TODO add your handling code here:
        apostador=Apostar.cinco;
    }//GEN-LAST:event_cincoMouseClicked

    private void diezMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diezMouseClicked
        // TODO add your handling code here:
        apostador=Apostar.diez;
    }//GEN-LAST:event_diezMouseClicked

    private void veinticincoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_veinticincoMouseClicked
        // TODO add your handling code here:
        apostador=Apostar.veinticinco;
    }//GEN-LAST:event_veinticincoMouseClicked

    private void cincuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cincuentaMouseClicked
        // TODO add your handling code here:
        apostador=Apostar.cincuenta;
    }//GEN-LAST:event_cincuentaMouseClicked

    private void setentaycincoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setentaycincoMouseClicked
        // TODO add your handling code here:
        apostador=Apostar.setentaycinco;
    }//GEN-LAST:event_setentaycincoMouseClicked

    private void cienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cienMouseClicked
        // TODO add your handling code here:
        apostador=Apostar.cien;
    }//GEN-LAST:event_cienMouseClicked

    private void retrocederMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retrocederMouseClicked
        // TODO add your handling code here:
 
    this.setVisible(false); // Oculta la ventana actual
    new Menu().setVisible(true); // Crea y muestra el menú principal con el dinero actual
    this.dispose(); // Libera los recursos de la ventana actual


    }//GEN-LAST:event_retrocederMouseClicked

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
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlackJack().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel D1;
    private javax.swing.JLabel D2;
    private javax.swing.JLabel J1;
    private javax.swing.JLabel J2;
    private javax.swing.JPanel cartasdealer;
    private javax.swing.JPanel cartasjugador;
    private javax.swing.JLabel cien;
    private javax.swing.JLabel cinco;
    private javax.swing.JLabel cincuenta;
    private javax.swing.JLabel diez;
    private javax.swing.JButton jugar;
    private javax.swing.JLabel mazo;
    private javax.swing.JButton monedero;
    private javax.swing.JLabel retroceder;
    private javax.swing.JLabel setentaycinco;
    private javax.swing.JLabel titulo;
    private javax.swing.JPanel todo;
    private javax.swing.JLabel veinticinco;
    // End of variables declaration//GEN-END:variables
}
