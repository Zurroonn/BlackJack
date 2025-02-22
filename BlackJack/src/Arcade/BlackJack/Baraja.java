package Arcade.BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Baraja {
    private ArrayList<String[]> cartas;
    private Random random;

    public Baraja() {
        cartas = new ArrayList<>();
        random = new Random();
        inicializarBaraja();
        barajar();
    }

    private void inicializarBaraja() {
        String[] palos = {"Clover", "Diamond", "Heart", "Spade"};
        String[] valores = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String palo : palos) {
            for (String valor : valores) {
                String nombre = "card_" + valor + "_" + palo;
                String rutaImagen = "/blackjack/assets/" + palo + "/" + nombre + ".png";

                cartas.add(new String[]{nombre, rutaImagen});
            }
        }
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public String[] robarCarta() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("La baraja está vacía.");
        }
        return cartas.remove(random.nextInt(cartas.size()));
    }

    public int getCartasRestantes() {
        return cartas.size();
    }
    
    public void reiniciarBaraja() {
    cartas.clear();  // Vacía la lista de cartas
    inicializarBaraja();  // Vuelve a llenar la baraja con todas las cartas
    barajar();  // Mezcla la baraja
}

}
