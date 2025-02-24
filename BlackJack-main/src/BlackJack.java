import java.util.Scanner;

public class BlackJack {
    private static Baraja baraja = new Baraja(); 
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenido a tu partida de blackjack");
        System.out.println("Tus cartas son:");
        int sumajugador = 0;

        for (int i = 0; i < 2; i++) {
            String cartaElegida = baraja.robarCarta();
            System.out.println(cartaElegida);
            sumajugador += comprobarNumero(cartaElegida, sumajugador);
        }

        System.out.println("Suma de tus cartas: " + sumajugador);
        System.out.println("La carta del dealer es:");
        int sumadealer = 0;
        String cartadealer = "";

        for (int i = 0; i < 2; i++) {
            String cartaElegida = baraja.robarCarta();
            if (i == 0) {
                cartadealer = cartaElegida;
            } else {
                System.out.println(cartaElegida);
            }
            sumadealer += comprobarNumero(cartaElegida, sumadealer);
        }

        if (verificarBlackJack(sumajugador, sumadealer, cartadealer)) {
            return; 
        }

        sumajugador = turnoJugador(sumajugador);

        if (sumajugador > 21) {
            System.out.println("Te has pasado. La segunda carta del dealer era: " + cartadealer);
            System.out.println("Fin del juego.");
            return;
        }

        System.out.println("La segunda carta del dealer es: " + cartadealer);
        sumadealer = turnoDealer(sumadealer, sumajugador);

        determinarGanador(sumajugador, sumadealer);
    }

    public static boolean verificarBlackJack(int sumajugador, int sumadealer, String cartadealer) {
        if (sumajugador == 21 && sumadealer != 21) {
            System.out.println("La segunda carta del dealer es: " + cartadealer);
            System.out.println("BlackJack! Ganaste");
            return true;
        }

        if (sumajugador == 21 && sumadealer == 21) {
            System.out.println("La segunda carta del dealer es: " + cartadealer);
            System.out.println("Ambos tienen BlackJack. Empate.");
            return true;
        }

        return false;
    }

    public static int turnoJugador(int sumajugador) {
        String eleccion;

        do {
            System.out.println("¿Quieres robar otra carta? (Y/N)");
            eleccion = sc.nextLine();

            if (eleccion.equalsIgnoreCase("Y")) {
                String cartaElegida = baraja.robarCarta();
                System.out.println("Robo un " + cartaElegida);
                sumajugador += comprobarNumero(cartaElegida, sumajugador);
                System.out.println("Suma actual: " + sumajugador);

                if (sumajugador >= 21) {
                    break;
                }
            }
        } while (eleccion.equalsIgnoreCase("Y"));

        return sumajugador;
    }

    public static int turnoDealer(int sumadealer, int sumajugador) {
        while (sumadealer < sumajugador && sumadealer < 21) {
            String cartaElegida = baraja.robarCarta();
            System.out.println("El dealer roba: " + cartaElegida);
            sumadealer += comprobarNumero(cartaElegida, sumadealer);
            System.out.println("Suma del dealer: " + sumadealer);
        }

        return sumadealer;
    }

    public static void determinarGanador(int sumajugador, int sumadealer) {
        if (sumadealer > 21) {
            System.out.println("El dealer se pasó. ¡Ganaste!");
        } else if (sumadealer == sumajugador) {
            System.out.println("Empate.");
        } else if (sumadealer > sumajugador) {
            System.out.println("El dealer gana con " + sumadealer);
        } else {
            System.out.println("¡Ganaste!");
        }
    }

    public static int comprobarNumero(String numero, int suma) {
        if (numero.contains("As")) {
            return (suma + 11 <= 21) ? 11 : 1;
        }
        if (numero.matches(".*(2|3|4|5|6|7|8|9|10).*")) {
            return Integer.parseInt(numero.replaceAll("\\D", ""));
        }
        return 10; // J, Q, K valen 10
    }
}
