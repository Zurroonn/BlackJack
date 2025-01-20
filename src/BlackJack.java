import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
//PICAS,CORAZONES,TREBOLES,DIAMANTES
	private static ArrayList<String> baraja = new ArrayList<>();
	private static Scanner sc= new Scanner(System.in);
	private static Random rd= new Random();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String []picas= {"AP","2P","3P","4P","5P","6P","7P","8P","9P","10P","JP","QP","KP",};
//		String []corazones= {"AC","2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC",};
//		String []treboles= {"AT","2T","3T","4T","5T","6T","7T","8T","9T","10T","JT","QT","KT",};
//		String []diamantes= {"AD","2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD",};		        
        baraja.add("As de Corazones"); baraja.add("2 de Corazones"); baraja.add("3 de Corazones"); baraja.add("4 de Corazones");
        baraja.add("5 de Corazones"); baraja.add("6 de Corazones"); baraja.add("7 de Corazones"); baraja.add("8 de Corazones");
        baraja.add("9 de Corazones"); baraja.add("10 de Corazones"); baraja.add("J de Corazones"); baraja.add("Q de Corazones"); baraja.add("K de Corazones");
        baraja.add("As de Diamantes"); baraja.add("2 de Diamantes"); baraja.add("3 de Diamantes"); baraja.add("4 de Diamantes");
        baraja.add("5 de Diamantes"); baraja.add("6 de Diamantes"); baraja.add("7 de Diamantes"); baraja.add("8 de Diamantes");
        baraja.add("9 de Diamantes"); baraja.add("10 de Diamantes"); baraja.add("J de Diamantes"); baraja.add("Q de Diamantes"); baraja.add("K de Diamantes");
        baraja.add("As de Tréboles"); baraja.add("2 de Tréboles"); baraja.add("3 de Tréboles"); baraja.add("4 de Tréboles");
        baraja.add("5 de Tréboles"); baraja.add("6 de Tréboles"); baraja.add("7 de Tréboles"); baraja.add("8 de Tréboles");
        baraja.add("9 de Tréboles"); baraja.add("10 de Tréboles"); baraja.add("J de Tréboles"); baraja.add("Q de Tréboles"); baraja.add("K de Tréboles");
        baraja.add("As de Picas"); baraja.add("2 de Picas"); baraja.add("3 de Picas"); baraja.add("4 de Picas");
        baraja.add("5 de Picas"); baraja.add("6 de Picas"); baraja.add("7 de Picas"); baraja.add("8 de Picas");
        baraja.add("9 de Picas"); baraja.add("10 de Picas"); baraja.add("J de Picas"); baraja.add("Q de Picas"); baraja.add("K de Picas");
		
		
		System.out.println("Bienvenido a tu partida de blackjack");
		System.out.println("Tus cartas son:");
		int sumajugador=0;
        for (int i = 0; i < 2; i++) {
            int indiceAleatorio = rd.nextInt(baraja.size());
            String cartaElegida = baraja.get(indiceAleatorio);
            System.out.println(cartaElegida);
            baraja.remove(indiceAleatorio);
            sumajugador=sumajugador+comprobarNumero(cartaElegida,sumajugador);
            
        }
        if (sumajugador==21) {
			System.out.println("Felicidades has ganado");
			return;
		}
        
        System.out.println(sumajugador);
        System.out.println("La carta del dealer es:");
        int sumadealer=0;
        int cont=0;
        String cartadealer="";
        for (int i = 0; i < 2; i++) {
        	cont++;
            int indiceAleatorio = rd.nextInt(baraja.size());
            String cartaElegida = baraja.get(indiceAleatorio);
            if (cont!=1) {
            	System.out.println(cartaElegida);
			}
            if (cont==1) {
				cartadealer=cartaElegida;
			}
            baraja.remove(indiceAleatorio);
            sumadealer=sumadealer+comprobarNumero(cartaElegida,sumadealer);
        }
        
        String eleccion="Y";
        int suma2=0;
        int sumas=sumajugador;
        do {
        System.out.println("Quieres robar otra carta?Y/N");
        eleccion= sc.nextLine();
        
        if (eleccion.equals("Y")||eleccion.equals("y")) {
			 sumas=robar(sumajugador)+sumas;
			 System.out.println(sumas);
			 if (sumas>=21) {
				break;
			}
		}
        }while(eleccion.equals("Y")||eleccion.equals("y"));
        if (sumas==21) {
			System.out.println("Felicidades has ganado");
			System.out.println("Gracias por jugar");
			return;
		}
        if (sumas>21) {
			System.out.println("Te has pasado");
			System.out.println("Gracias por jugar");
			return;
		}
        System.out.println("La segunda carta del dealer es: "+cartadealer);
        int sumas2=sumadealer;
        if (sumas>sumadealer) {
			sumas2=robar(sumadealer+sumas2);
			System.out.println(sumas);
			if (sumas<) {
				
			}
			return;
		}if (sumas==sumadealer) {
			System.out.println("Empate");
			System.out.println("Gracias por jugar");
			return;
		}if (sumas<sumadealer) {
			System.out.println("Perdiste");
			System.out.println("Gracias por jugar");
			return;
		}
       
        
    }
	public static int comprobarNumero(String numero, int suma) {
		if (numero.contains("As")) {
			if (((suma+11)<=21)) {
				return 11;
			}
		return 1;
		}
		if (numero.contains("2")) {
			return 2;
			}
		if (numero.contains("3")) {
			return 3;
			}
		if (numero.contains("4")) {
			return 4;
			}
		if (numero.contains("5")) {
			return 5;
			}
		if (numero.contains("6")) {
			return 6;
			}
		
		if (numero.contains("7")) {
			return 7;
			}
		if (numero.contains("8")) {
			return 8;
			}
		if (numero.contains("9")) {
			return 9;
			}
		if (numero.contains("10")) {
			return 10;
			}
		if (numero.contains("J")) {
			return 10;
			}
		if (numero.contains("Q")) {
			return 10;
			}
		if (numero.contains("K")) {
			return 10;
			}
		return 0; 
	}
	public static int robar(int suma) {
            int indiceAleatorio = rd.nextInt(baraja.size());
            String cartaElegida = baraja.get(indiceAleatorio);
            System.out.println(cartaElegida);
            baraja.remove(indiceAleatorio);
            int carta=comprobarNumero(cartaElegida, suma);
           
        return carta;
	}
	public static boolean ganador(int sumajugador, int sumadealer) {
		
		return true;
	}
}
