/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merklehellman;

import java.awt.Label;
import java.util.Arrays;
import javax.swing.JLabel;

/**
 *
 * @author marti
 */
public class Merkle {
    
    
    //Metodo de cifrado
    public static int[] cifrado (int [] clavePublica, String texto_binario){
        //System.out.println("\nCifrado.....");

        //String texto_binario = convetirTextoBin(texto); //Convierte el texto a binario
        String [] divide = dividirBits(texto_binario);

        int [] txt_cifrado = new int[divide.length];
        int contador = 0 ;
        int suma = 0;

        for (String s : divide) {
            char[] separa = s.toCharArray();
            for (int j = 0; j < separa.length; j++) {
                if (separa[j] == '1') {
                    suma = suma + clavePublica[j];
                }
            }
            txt_cifrado[contador] = suma;
            contador = contador + 1;
            suma = 0;
        }

        /*System.out.print("\nTexto cifrado: ");
        for (int x:txt_cifrado) {
            System.out.print(x +" ");
        }*/

        return txt_cifrado;
    }

    //0110100101100011001101110011000000110000
    //0110100101101100110011101100000011000000

    //Metodo descifrado
    public static String descifrado(int [] txt_cifrado, int [] mochila, int m, int w_inversa, JLabel moduloArreglo){

        int [] descifrar = new int[txt_cifrado.length];
        int resultado = 0;
        int contador = 0;
        for (int c : txt_cifrado) {
            resultado = ((w_inversa * c) % m)% m;
            descifrar[contador] = resultado;
            contador = contador+1;
            //descifrar.append(resultado);
        }

        StringBuilder binario = new StringBuilder();
        StringBuilder inverso = new StringBuilder();
        StringBuilder modulos = new StringBuilder();

        int compara  = 0;
        int conta    = 0;
        int anterior = 0;

        
        for (int modulo : descifrar){
            for (int s = 3; s >= 0; s-- ){
                compara = mochila[s];
                if (compara <= modulo && anterior == 0 && modulo !=0){
                    binario.append("1");
                    anterior = compara;
                }
                else if (compara <= modulo && anterior > 0  && modulo != 0){
                    if (anterior + compara <=modulo){
                        binario.append("1");
                        anterior = anterior+compara;
                    }else {
                        binario.append("0");
                    }
                }
                else {
                    binario.append("0");
                }

            }
            anterior = 0;//no mover
            //System.out.print( modulo+" ");
            modulos.append(modulo+" ");
            inverso.append(binario.reverse());
            binario = new StringBuilder();
        }
        String moduloF = modulos.toString();
        moduloArreglo.setText(moduloF);
        //System.out.println("\nTexto descifrado a binario: "+inverso);
        convetirTextoDec(inverso.toString());
        return inverso.toString();
    }



    public static String[] dividirBits(String texto_binario){

        int contador_bit  =0;
        int contador_bits =0;

        StringBuilder c_bits = new StringBuilder();
        String [] divide = new String[texto_binario.length()/4]; // Arreglo para almacenar los bits
        char [] texto_letras = texto_binario.toCharArray();      // Divide el texto en letra x letra

        for (char c : texto_letras) {                            // Recorre el arreglo de letras
            c_bits.append(c);
            contador_bit = contador_bit + 1;

            if (contador_bit > 3) {
                divide[contador_bits]=c_bits.toString();
                contador_bits = contador_bits+1;

                c_bits=new StringBuilder();                      // Limpia al builder
                contador_bit = 0;                                // Resetea el contador inicial
            }
        }

        /*System.out.print("Binario a 4 bits: ");
        for (String s : divide) {
            System.out.print(s +"  ");
        }*/
        return divide;
    }

    public static String convetirTextoBin(String texto) {
        StringBuilder binario = new StringBuilder();
        String bin = null;
        int aux = 0;

        for (int i = 0; i < texto.length(); i++){
            aux = texto.charAt(i);
            bin = Integer.toBinaryString(aux);
            bin = String.format("%8s", bin).replace(' ','0');
            binario.append(bin);
        }
        //System.out.println("Texto a binario: "+binario.toString());
        return binario.toString();
    }

    public static String convetirTextoDec(String texto) {

        StringBuilder bin_texto = new StringBuilder();
        Arrays.stream(texto.split("(?<=\\G.{8})")).forEach(s -> bin_texto .append((char) Integer.parseInt(s, 2)));
        //System.out.println("Texto descifrado: "+bin_texto);
        return bin_texto.toString();
    }


    
}
