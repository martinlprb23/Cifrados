/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sustitucion;

import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class Cesar {
    
     //Método para cifrar el texto
    public static String cifradoCesar(String texto, int n) {

        StringBuilder cifrar = new StringBuilder();
        n = n % 26; // Operador mod de n % 26 (26 total de caracteres del abcdario)
        //recorre el string texto quitando espacios adicionales

        for (int i = 0; i < texto.trim().length(); i++) {
            /*Valida caracter por caracter para verificar si se encuentra
            el caracter Minusculas*/

            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z' ) {
                //verifica que la posicion + n  se mayor a z
                if ((texto.charAt(i) + n) > 'z') {
                    //se le suma el valor de n - 26
                    cifrar.append((char) (texto.charAt(i) + n - 26));
                } else {
                    //se suma el valor de n
                    cifrar.append((char) (texto.charAt(i) + n));
                }
            }

            /*valida caracter por caracter para verificar si se encuentra
            el caracter  Mayusculas*/
            else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z' ) {
                //verifica que la posicion + n  se mayor a Z
                if ((texto.charAt(i) + n) > 'Z') {
                    //se le suma el valor de n - 26
                    cifrar.append((char) (texto.charAt(i) + n - 26));
                } else {
                    //se suma el valor de n
                    cifrar.append((char) (texto.charAt(i) + n));
                }
            }

            //Valida si existe un espacio
            else if (texto.charAt(i) == ' '){
                cifrar.append(" ");//sustituye por un espacio en blanco
            }

            //Muestra un mensaje de alerta ante caracteres no validos
            else {
               
                JOptionPane.showMessageDialog(null, "Al parecer el campo de texto contine caracteres no validos." +
                        "\n Revise su texto.","Error" , JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }

        /*Instancia a la clase creada Alert Dialog para mostrar el resultado pasando los parametros
        * titulo, descripcion, y el mensaje a mostrar*/
        //new AlertDialog().dialog("CESAR", "El mensaje encriptado es:",cifrar.toString());
        JOptionPane.showMessageDialog(null, "El mensaje encriptado es: "+cifrar.toString());
        return cifrar.toString();
    }

    //Método para cifrar el texto
    public static String descifradoCesar(String texto, int n) {
        StringBuilder descifrado = new StringBuilder();
        n = n % 26;  // Operador mod de n % 26 (26 total de caracteres del abcdario)
        //recorre el string texto quitando espacios adicionales
        for (int i = 0; i < texto.trim().length(); i++) {
             /*valida caracter por caracter para verificar si se encuentra
            el caracter Minusculas*/
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                //verifica si n es menor a a
                if ((texto.charAt(i) - n) < 'a') {
                    //asigna el valor en i restandole n + 26
                    descifrado.append((char) (texto.charAt(i) - n + 26));
                } else {
                    //asigna el valor en i - n
                    descifrado.append((char) (texto.charAt(i) - n));
                }
            }

            /*valida caracter por caracter para verificar si se encuentra
            el caracter Mayusculas*/
            else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - n) < 'A') {
                    descifrado.append((char) (texto.charAt(i) - n + 26));
                } else {
                    descifrado.append((char) (texto.charAt(i) - n));
                }
            }

            //Valdia si exiten espacios
            else if (texto.charAt(i)==' '){
                //agrega un espacio
                descifrado.append(" ");
            }

            //Valida si existe algun error
            else {
                JOptionPane.showMessageDialog(null, "Al parecer el campo de texto contine caracteres no validos." +
                        "\n Revise su texto.","Error" , JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        /*Instancia a la clase creada Alert Dialog para mostrar el resultado pasando los parametros
         * titulo, descripcion, y el mensaje a mostrar*/
        JOptionPane.showMessageDialog(null, "El mensaje desencriptado es: "+descifrado.toString());
        return descifrado.toString();
    }
    
}
