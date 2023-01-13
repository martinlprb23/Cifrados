/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transposicion;
/*** @author marti*/
public class Filas {
    
    //Metodo para calcular el numero de columas de la matriz
    public static int obtenerColumna (String mensaje, int fila){
        int columna;
        double tam = mensaje.length(); //tamaño de texto
        columna = (int) Math.ceil(tam / fila); //formula
        return columna;
    }
    
    //Metodo para crear la matriz
    public static char[][] Crear_Matriz(StringBuilder mensaje, int fila, int columna, boolean cifrar){
        //LLena el arreglo por completo metiendo espacios vacios al final
        while (true) {
            if (mensaje.length() < fila * columna) {
                mensaje.append(" ");
            } else {
                break;
            }
        }
        //matriz 
        char[][] matriz = new char[fila][columna];
        
        //true para llenar la matriz encriptada
        if (cifrar){
            //llena la matriz por filas
            for (int f = 0; f < fila; f++) {
                for (int c = 0; c < columna; c++) {
                    matriz[f][c] = mensaje.charAt(f + c * fila);
                }
            }
        //llena la matriz por columnas  
        }else{
            for (int f = 0; f < fila; f++) {
                for (int c = 0; c < columna; c++) {
                    matriz[f][c] = mensaje.charAt(c + f * columna);
                }
            }
        }
        return matriz; //retorna la matriz creada
    }

    //Metodo para obtener el texto cifrado
    public static String cifrar (char [] [] matriz, int num_filas, int num_columnas){

        StringBuilder msg_cifrado = new StringBuilder("");
        //Recorre y guarda el contenido cifrado en el StringBuilder
        for (int i = 0; i < num_filas; i++) {
            for (int j = 0; j < num_columnas; j++) {
                msg_cifrado.append(matriz[i][j]);
            }
        }
        return msg_cifrado.toString();
    }

    //Metodo para obtener el texto descifrado
    public static String descrifrar(char [] [] matriz, int num_filas, int num_columnas ){
        StringBuilder descifrado = new StringBuilder("");
         //Recorre y guarda el contenido descifrado en el StringBuilder
        for (int i = 0; i < num_columnas; i++) {
            for (int j = 0; j < num_filas; j++) {
                descifrado.append(matriz[j][i]);
            }
        }
        return descifrado.toString();
    }
    
}
