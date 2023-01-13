/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sustitucion;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class Polybios {
   
     //Variables globales
    public static String msg_cifrado = "";
    public static String msg_descifrado = "";

    //Arreglos cifrado y no cifrado
    public  static String[] normal =
            {"A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," "};

    public  static String [] cifrado = {"AA","AB","AC","AD","AE","BA","BB",
            "BC","BD","BD","BE","CA","CB","CC","CD","CE","DA","DB","DC","DD",
            "DE","EA","EB","EC","ED","EE"," "};

    //Arreglos para alamcenar los resultados
    public  static ArrayList<String> resultado = new ArrayList<String>();
    public  static ArrayList<String> resultad2 = new ArrayList<String>();

    //METODO DE ENCRIPTACION POLYBIOS
    public static String Encriptar(String text_user){

        msg_cifrado = "";//Inicializacion de variable
        resultado.clear();//limpia el arreglo

        //Separa cada caracter y lo mete en un arreglo
        String[] texto = text_user.trim().toUpperCase().split("");

        //Recorre el arreglo
        for (String value : texto) {
            //recorre el arreglo "normal"
            for (int j = 0; j < normal.length; j++) {
                //valida si existe coincidencia
                if (value.equals(normal[j])) {
                    //Almacena en el arreglo el valor en la posicion j
                    resultado.add(cifrado[j]);
                }
            }
        }
        //Recorre el arreglo para crear un string
        for (String s : resultado) {
            //Aumenta los caracteres para formar el string
             msg_cifrado += s;
        }

        /*Instancia a la clase creada Alert Dialog para mostrar el resultado pasando los parametros
         * titulo, descripcion, y el mensaje a mostrar*/
        //new AlertDialog().dialog("POLYBIOS", "El mensaje encriptado es:", msg_cifrado.toString());
        JOptionPane.showMessageDialog(null, "El texto cifrado es:" +msg_cifrado);
        return msg_cifrado; 
    }

    //METODO DE DESENCRIPTACION POLYBIOS
    public static void Desencriptar(String text){
        msg_descifrado = "";
        resultad2.clear();//limpia el arreglo
        //divide la cadena de caracteres en una catidad de 2 ejemplo [AA,BB]
        String[] texto = text.trim().replace(" ", "  ").split("(?<=\\G.{2})");

        for ( int x = 0; x < texto.length; x++ ) {
            if ("  ".equals(texto[x])) //Verifica los espacios dobles
            { texto[x] = " ";}//Remplaza por un espacio
            //Recorre el arreglo
            for ( int y = 0; y < cifrado.length; y++ ) {
                //valida si existe coincidencia
                if ( texto[x].equals(cifrado[y]) ) {
                    //Almacena en el arreglo el valor en la posicion y
                    resultad2.add(normal[y]);
                }
            }
        }
        //Recorre el arreglo para crear un string
        for (String s : resultad2) {
            //Aumenta los caracteres para formar el string
            msg_descifrado += s;
        }

        /*Instancia a la clase creada Alert Dialog para mostrar el resultado pasando los parametros
         * titulo, descripcion, y el mensaje a mostrar*/
        //new AlertDialog().dialog("POLYBIOS", "El mensaje desencriptado es:", msg_descifrado);
        JOptionPane.showMessageDialog(null, "El texto descifrado es:" +msg_descifrado);
    }

    
}
