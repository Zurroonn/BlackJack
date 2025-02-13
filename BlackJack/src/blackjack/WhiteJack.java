/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package blackjack;

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

/**
 *
 * @author aazur
 */
public class WhiteJack extends javax.swing.JFrame {
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static int dinerousuario=0;
    private List<JLabel> cartasJugador = new ArrayList<>();
    private List<JLabel> cartasDealer = new ArrayList<>();


    private static Baraja baraja = new Baraja();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Creates new form WhiteJack
     */
    public WhiteJack() {
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
    System.out.println("Añadiendo carta al jugador: " + cartaElegida[0]);

    jPanel2.add(nuevaCarta);
    jPanel2.revalidate();
    jPanel2.repaint();

    sumajugador += comprobarNumero(cartaElegida[0], sumajugador);
    return sumajugador;
    }


    public int turnoDealer(int sumadealer, int sumajugador) {
        while (sumadealer < sumajugador && sumadealer <= 17) {
            String[] cartaElegida = baraja.robarCarta();
            ImageIcon imagenCarta = new ImageIcon(getClass().getResource(cartaElegida[1]));

            JLabel nuevaCarta = new JLabel();
            nuevaCarta.setIcon(imagenCarta);
            jPanel3.add(nuevaCarta);  // Agregar la carta al panel del dealer
            jPanel3.revalidate();
            jPanel3.repaint();
            cartasDealer.add(nuevaCarta);
            sumadealer += comprobarNumero(cartaElegida[0], sumadealer);
        }

        return sumadealer;
    }

    private static void iniciarSesion() {
        String usuario = JOptionPane.showInputDialog(null, "Introduce tu usuario:", "Inicio de sesión", JOptionPane.QUESTION_MESSAGE);
        if (usuario == null) return; // Si cancela, salir

        String contraseña = JOptionPane.showInputDialog(null, "Introduce tu contraseña:", "Inicio de sesión", JOptionPane.QUESTION_MESSAGE);
        if (contraseña == null) return;

        // Leer el archivo y buscar el usuario
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                dinerousuario=Integer.parseInt(datos[2]);
                if (datos.length == 3 && datos[0].equals(usuario) && datos[1].equals(contraseña)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario + "! \nDinero disponible: $" + datos[2], "Sesión iniciada", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método para crear un usuario nuevo
    private static void crearUsuario() {
        String usuario = JOptionPane.showInputDialog(null, "Elige un nombre de usuario:", "Crear usuario", JOptionPane.QUESTION_MESSAGE);
        if (usuario == null) return;

        String contraseña = JOptionPane.showInputDialog(null, "Elige una contraseña:", "Crear usuario", JOptionPane.QUESTION_MESSAGE);
        if (contraseña == null) return;

        String dineroStr = JOptionPane.showInputDialog(null, "Introduce la cantidad de dinero inicial:", "Crear usuario", JOptionPane.QUESTION_MESSAGE);
        if (dineroStr == null) return;

        try {
            int dinero = Integer.parseInt(dineroStr);
            dinerousuario=dinero;
            // Guardar el usuario en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
                writer.write(usuario + "," + contraseña + "," + dinero);
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Usuario creado con éxito. Bienvenido, " + usuario + "!", "Usuario creado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debes ingresar un número válido para el dinero.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public  void determinarGanador(int sumajugador, int sumadealer) {
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

    public static int comprobarNumero(String numero, int suma) {    //PROBLEMA GRAVE SOLUCIONADO CON LAS FIGURAS CAMBIANDO EL NOMBRE POR SU LETRA

        if (numero.matches(".*(2|3|4|5|6|7|8|9|10).*")) {
            return Integer.parseInt(numero.replaceAll("\\D", ""));
        }
        if (numero.matches(".*(1).*")) {
            return (suma + 11 > 21) ? 1 : 11;
        }
        return 10; // J, Q, K valen 10
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mazo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        D2 = new javax.swing.JLabel();
        D1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        J1 = new javax.swing.JLabel();
        J2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 0));

        mazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N

        jButton1.setText("Empezar a jugar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/monedas/fichas-de-casino (1) (1).png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 204, 0));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        D2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        jPanel3.add(D2);

        D1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        jPanel3.add(D1);

        jPanel2.setBackground(new java.awt.Color(0, 204, 0));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        J1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        jPanel2.add(J1);

        J2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/Different-BikeBack.png"))); // NOI18N
        jPanel2.add(J2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(mazo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton2)))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(412, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(mazo)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(jButton1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(102, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(51, 51, 51))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            for (JLabel carta : cartasJugador) {
        jPanel2.remove(carta);
    }
             for (JLabel carta : cartasDealer) {
        jPanel3.remove(carta);
    }
    cartasJugador.clear(); // Limpiar la lista
        ImageIcon icon = new ImageIcon("Different-BikeBack.png");
        J1.setIcon(icon);
        J2.setIcon(icon);
        D1.setIcon(icon);
        D2.setIcon(icon);

        baraja.reiniciarBaraja();
       
         
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Usted tiene $"+dinerousuario);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(WhiteJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WhiteJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WhiteJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WhiteJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
 // Mensaje de bienvenida
        JOptionPane.showMessageDialog(null, "¡Bienvenido al juego de Blackjack!", "Bienvenida", JOptionPane.INFORMATION_MESSAGE);

        // Opciones de inicio
        String[] opciones = {"Iniciar sesión", "Crear usuario"};
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona una opción para continuar:",
                "Menú",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == 0) {
            iniciarSesion();
        } else if (seleccion == 1) {
            crearUsuario();
        }
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WhiteJack().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel D1;
    private javax.swing.JLabel D2;
    private javax.swing.JLabel J1;
    private javax.swing.JLabel J2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel mazo;
    // End of variables declaration//GEN-END:variables
}
