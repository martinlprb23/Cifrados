/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sustitucion;

import javax.swing.JOptionPane;

/**
 *
 * @author martin
 */
public class Trithemius {
    
     //Arreglos para el abcdario en mayusculas y minusculas
    static String[] Mayusculas =
            {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O",
                    "P","Q","R","S","T","U","V","W","X","Y","Z"};

    static String[] Minusculas =
            {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o",
                    "p","q","r","s","t","u","v","w","x","y","z"};



    //Metodo para cifrar el texto 
    public static String cifrar(String texto, int key){
        //Divide el texto en letra x letra
        String[] texto_dividio = texto.trim().split("");
        //variables auxiliares
        int posicion =  0;
        int contador =  0;
        //Builder para aumentar el resultado final uwu
        StringBuilder cifrado = new StringBuilder();
        for (String s : texto_dividio) {                    //recorre el texto
            for (int i = 0 ; i<Mayusculas.length; i++)      //recorre el arreglo de Mayusculas
            {
                if (s.equals(Mayusculas[i]))                //busca si existe coincidencia
                {
                    posicion = i + key + contador;          //obtiene la posicion del arreglo 
                    contador = contador+1;                  //contador para el aumento 
                    if (posicion> Mayusculas.length-1) {    //valida que no sobrepase el tamaño del arreglo
                        posicion = posicion-27;             //empieza de nuevo el arreglo
                    }
                    cifrado.append(Mayusculas[posicion]);   //Agrega al builder el contenido del arreglo
                    posicion = 0;                           //Resetea la posicion

                } else if (s.equals(Minusculas[i])){        //Aplica lo mismo que el recorrido de las
                    posicion = i + key + contador;          //mayusculas solo que aqui lo hace con las 
                    contador = contador+1;                  //minusculas UwU
                    if (posicion> Minusculas.length-1) {
                        posicion = posicion-27;
                    }
                    cifrado.append(Minusculas[posicion]);
                    posicion = 0;
                }

                else if (s.equals(" ") && i == 26){         //Valida si existen espacios y los agrega
                    cifrado.append(" ");                    //Agrega el espacio
                }

            }
        }
        JOptionPane.showMessageDialog(null, "El mensaje encriptado es: "+cifrado.toString());
        return cifrado.toString();
    }

    //Metodo para descifrar UWU
    public static void descifrar(String texto, int key){
        //Divide el texto en letra x letra
        String[] texto_dividio = texto.trim().split("");
        //Variables auxiliares
        int posicion =  0;
        int contador =  0;
        //Builder para guardar el resultado
        StringBuilder descifrado = new StringBuilder();
        for (String s : texto_dividio) {                        //Recorre el texto cifrado
            for (int i = 0 ; i<Mayusculas.length; i++)          //Recorre el arreglo de Mayusculas
            {
                if (s.equals(Mayusculas[i]))                    //Busca si existen coincidencias
                {
                    posicion = i - key - contador;              //Obtiene la posicion original
                    contador = contador+1;                      //Aumenta el contador
                    if (posicion < 0) {                         //Valida que la posicion no sea menor    
                        posicion = posicion+27;                 //al arreglo 
                    }
                    descifrado.append(Mayusculas[posicion]);    //Agrega la coincidencia al builder
                    posicion = 0;                               //Resetea la posicion

                } else if (s.equals(Minusculas[i])){            //Aplica lo mismo solo que con el 
                    posicion = i - key - contador;              //arreglo de las minusculas
                    contador = contador+1;
                    if (posicion < 0) {
                        posicion = posicion+27;
                    }
                    descifrado.append(Minusculas[posicion]);
                    posicion = 0;
                }

                else if (s.equals(" ") && i == 26){             //Valida los espacios
                    descifrado.append(" ");                     //Agrega el espacio
                }

            }
        }
        JOptionPane.showMessageDialog(null, "El mensaje desencriptado es: "+descifrado.toString());
    }
}
