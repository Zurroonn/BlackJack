import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Baraja {
    private ArrayList<String> cartas;
    private Random random;

    public Baraja() {
        cartas = new ArrayList<>();
        random = new Random();
        inicializarBaraja();
        barajar();
    }

    private void inicializarBaraja() {
        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String palo : palos) {
            for (String valor : valores) {
                cartas.add(valor + " de " + palo);
            }
        }
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public String robarCarta() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("La baraja está vacía.");
        }
        return cartas.remove(random.nextInt(cartas.size()));
    }

    public int getCartasRestantes() {
        return cartas.size();
    }
}
