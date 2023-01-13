/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transposicion;

/** @author marti*/
public class Columnas {
    
    // Declaracion de matrices
    char[][] arr_matriz;
    char[][] cifrado;
    char[][] descrifrado;
    char[] keya;
    char[] key_temporal;
    
    //Metodo para crear la matriz encriptada
    public  void crear_matriz_e(String s, String key, int fila, int columna){
        arr_matriz =new char[fila][columna];
        int contador=0;
        keya=key.toCharArray();
        for(int i=0;i<fila;i++)
        {
            for(int j=0;j<columna;j++)
            {
                if(contador<s.length()){
                    arr_matriz[i][j]=s.charAt(contador);
                    contador++;
                }
                else{
                    arr_matriz[i][j]=' ';
                }
            }
        } 
    }
    
    //Metodo para crear la llave 
    public void crearKey(String key, int columna)
    {
        key_temporal=key.toCharArray();
        for(int i=0;i<columna-1;i++)
        {
            for(int j=i+1;j<columna;j++)
            {
                if(key_temporal[i]>key_temporal[j])
                {
                    char temp=key_temporal[i];
                    key_temporal[i]=key_temporal[j];
                    key_temporal[j]=temp;
                }
            }
        }    //System.out.println(key_temporal);
    }
    
    
    //Metodo para crear la matriz desencriptada UwU
    public void crear_matriz_D(String mensaje, String key, int fila, int columna){
        arr_matriz =new char[fila][columna];
        int k=0;
        keya=key.toCharArray();
        for(int i=0;i<columna;i++) {
            for(int j=0;j<fila;j++){
                if(k<mensaje.length()){
                    arr_matriz[j][i]=mensaje.charAt(k);
                    k++;
                }
                else{
                    arr_matriz[j][i]=' ';
                }
            }
        }
    }
    
    
    //Metodo para guardar el arreglo cifrado XD
    public char[][] cifrar(int fila, int columna) {
        cifrado =new char[fila][columna];
        for(int i=0;i<columna;i++){
            for(int j=0;j<columna;j++) {
                if(keya[i]==key_temporal[j]){
                    for(int k=0;k<fila;k++){
                        cifrado[k][j]= arr_matriz[k][i];
                    }
                    key_temporal[j]='?';
                    break;
                }
            }
        }
        return cifrado;
    }
    
    //Metodo para guardar el arreglo descifrado XD
    public char[][] descrifrar(int fila, int columna) {
        descrifrado =new char[fila][columna];
        for(int i=0;i<columna;i++){
            for(int j=0;j<columna;j++){
                if(keya[j]==key_temporal[i]){
                    for(int k=0;k<fila;k++){
                        descrifrado[k][j]= arr_matriz[k][i];
                    }
                    keya[j]='?';
                    break;
                }
            }
        } 
        return descrifrado;    
    }
    
    
//--------Los poderosos metodos para imprimir el mensaje cifrado o descifrado UwU
          public String resultE(int row, int column, char[][] arr){
            StringBuilder msg_cifrado = new StringBuilder("");
            for(int i=0;i<column;i++){
                for(int j=0;j<row;j++) {
                    msg_cifrado .append(arr[j][i]);
                }
            }
            return msg_cifrado.toString();
        }
                
        public String resultD(int row, int column, char[][] arr) {
            StringBuilder ms_descifrado = new StringBuilder("");
            for(int i=0;i<row;i++) {
                for(int j=0;j<column;j++){
                    ms_descifrado.append(arr[i][j]);
                }
            }
            return ms_descifrado.toString();
        }
}
