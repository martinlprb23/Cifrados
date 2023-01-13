/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transposicion;

/** @author marti*/
public class Vayas {
    
    //Metodo para obtener la posicion :( 3 horas jaja
    private static int obtenerTerm(int iteration, int fila, int tam) {
        if ((tam == 0) || (tam == 1)) {
            return 1;
        }
        if((fila == 0) || (fila == tam-1)) { //(tame-1)*2
            return (tam-1)*2;
        }

        if (iteration % 2 == 0) { 
            return (tam-1-fila)*2;
        }
        return 2*fila;
    }

    //Metodo para cifrar UwU
    public static String cifrar(String message, int key) {
        if (key < 0) {
            throw new ArithmeticException("Numero Negativo");
        } else if (key == 0) {
            key = 1;
        }
        StringBuilder msg_cifrado = new StringBuilder();

        for(int row = 0; row < key; row++) { // fila
            int iter = 0; // Contador 
            for(int i = row; i < message.length(); i += obtenerTerm(iter++, row, key)) {
                msg_cifrado.append(message.charAt(i)); // llenamos  el StringBuilder
            }
        }
        return msg_cifrado.toString();
    }

    //Metodo para descifrar UwU
    public static String descifrar(String mensaje, int key) {
        if (key < 0) {
            throw new ArithmeticException("Numero negativo ");
        }
        StringBuilder msg_descifrado = new StringBuilder(mensaje);
        int posicion = 0; // Position in source string
        for(int row = 0; row < key; row++) { // filas
            int iter = 0; // contador
            for(int i = row; i < mensaje.length(); i += obtenerTerm(iter++, row, key)) {
                msg_descifrado.setCharAt(i, mensaje.charAt(posicion++));
            }
        }
        return msg_descifrado.toString();
    }
  
}
