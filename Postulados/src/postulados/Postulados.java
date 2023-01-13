/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postulados;


import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 * @author marti
 */
public class Postulados {
    
     public static int MAX = 0;
     
    //Metodo para convertir el txt a binario
    public static String txt_binario(String mensaje){

        StringBuilder msg= new StringBuilder();
        char [] strToChar = mensaje.toCharArray (); //matriz de caracteres
        //recorremos toda la matriz
        for (char c : strToChar)
        {   //Convertimos a binario
            String bin = Integer.toBinaryString(c);
            //Se completan los 8 bits ejemplo 1111111 -> 01111111
            String binario = String.format("%8s", bin).replace(' ', '0');
            msg.append(binario);//se le agrega al builder
        }
        //retorna el texto en binario
        return msg.toString();
    }

    //Metodo para realizar el postulado G1
    public static void postulado_uno(String binario, JLabel ceros, JLabel unos, JLabel Postulado){
        boolean cumple;//para verificar si se cumple el postulado
        //Variables contadores
        int contador_0 = 0;
        int contador_1 = 0;
        //String a matriz de caracteres
        char [] strToChar = binario.toCharArray ();
        //Recorremos el arreglo
        for (char c : strToChar) {
            if (c == '0') {
                //cuenta el numero Total de Ceros
                contador_0 = contador_0+1;
            }else if (c == '1'){
                //cuenta el numero Total de Unos
                contador_1 = contador_1+1;
            }
        }
        //Valor de la resta de los contadores
        int resultado = (contador_0 - contador_1);
        //Valida y convierte a positivo
        if (resultado<0) {
            resultado = resultado * -1;
        }

        //Si resultado es 0 o 1 de diferencia es verdadero
        cumple = resultado == 0 || resultado == 1;
        ceros.setText("Numero total de 0: "+contador_0);
        unos.setText("Numero total de 1: "+contador_1);
        Postulado.setText("Cumple el postulado G1: "+cumple);
        //System.out.println("Num ceros: "+contador_0+"\nNum unos: "+contador_1+"\nPostulado 1: "+cumple);

    }



    public static void postulado_dos(String binario, DefaultTableModel tablaRachas, JLabel Postulado){
        //Convierte en arreglo el txt
        char [] strToChar = binario.toCharArray ();

        //System.out.println(binario+ "\nT: "+binario.length());
        //Arreglos auxiliares
        String [] arr_0 = new String[strToChar.length];
        String [] arr_1 = new String[strToChar.length];
        //Builders auxiliares
        StringBuilder ceros = new StringBuilder("");
        StringBuilder unos  = new StringBuilder("");
        //Rercoore el arreglo
        for (int x = 0;x< strToChar.length; x++){
            //Recoore y aumenta el numero 0 o 1
            /*Ejemplo 0,00,000,000...*/
            for (int i = 0; i < x; i++) {
                ceros.append('0');
                unos.append('1');
            }
            //Coloca el valor en cada arreglo
            arr_0[x] = ceros.toString();
            arr_1[x] = unos.toString();
            //Reinicia el string builder
            ceros = new StringBuilder("");
            unos = new StringBuilder("");
        }
        
        //Variable para crear un contador
        String count_0 =binario;
        //Recorre  el arreglo  en reversa
        for (int i = arr_0.length; i>2; i --){
            String replace_0 = arr_0[i-1];//Obtienen el valor a reemplazar
            String cuantos_0 = String.valueOf(replace_0.length());//Obtiene el numero de 0
            //Se reemplazan para obtener los siguiente
            /*bin 10011101011
            * res 1211101011*/
            count_0 = String.format("%"+i+"s", count_0).replaceAll(replace_0,cuantos_0);
        }

        String count_1 =count_0;

        for (int i = arr_1.length; i>2; i--) {
            String replace_1 = arr_1[i-1];
            String cuantos_1 = String.valueOf(replace_1.length());
            //Se reemplazan para obtener los siguiente
            /*bin  1211101011
             * res 1230102*/
            count_1 = String.format("%"+i+"s", count_1).replaceAll(replace_1,cuantos_1);
        }
        //Limpia los 1 o 0 sobrantes ejem: 1230102 -> 232
        String limpiar  = "";
        limpiar = String.format("%s", count_1).replaceAll("1","");
        String rachas_f = "";
        rachas_f = String.format("%s", limpiar).replaceAll("0","");

        //System.out.println(rachas_f.trim());
        //Convierte a arreglo
        char [] divide = rachas_f.trim().toCharArray();
        int sum = 0;//Valor total de las rachas mayores a longitud 1
        for (char value : divide) { //Recorre el arreglo
            String c = String.valueOf(value);//Convierte a string
            sum += Integer.parseInt(c);//Se le suma el valor
        }
        //Restante de del total de bits - total de rachas mayores a 1
        int restante = strToChar.length- sum;
        tablaRachas.setRowCount(0);
        //LLamada al metodo para calcular las rachas
        calcularRachas(rachas_f.trim().toCharArray(), strToChar.length, restante, tablaRachas, Postulado);
    }

    //ESTE METODO ME COSTO UNO Y LA MITAD DEL OTRO 
    private static void calcularRachas(char[] arreglo, int total, int restante,
            DefaultTableModel model_rachas, JLabel cumpleP) {
        //Valida si se cumple el postulaado G2
        boolean cumple = true;
        //Mitad del total de bits
        int  mitad = total / 2;
        //System.out.println();
        model_rachas.setRowCount(0);
        //Valida si la mitad de rachas de log 1 entra en el postualdo G1
        if (mitad==restante || mitad == restante+1 || mitad  == restante-1){
            //System.out.print(restante + " rachas de longitud 1\tSe cumple");
            model_rachas.addRow(new Object[]{restante+"  Rachas de longitud 1.\tSe cumple"});
        }else {
            //System.out.print(restante + " rachas de longitud 1\tNo se cumple")
            model_rachas.addRow(new Object[]{restante+"  Rachas de longitud 1.\tNo se cumple"});;
            cumple = false;//El postulado se chinga
        }
        //Arreglo auxiliar
        char[] auxiliar = new char[arreglo.length];
        clearA(auxiliar); //Se limpia
        int e = 0;//Auxilioar
        System.out.println();
        //Recorre el arreglo para contar las rachas
        for (char c : arreglo) {
            e = c;//Almacena en el valor de arreglo[n]
            mitad = mitad / 2;//Calcula las mitades
            if (!buscarElemento(auxiliar, e)) {//valida si existe el coincidencia
                int valor = contarElementos(arreglo, e); // valor de arreglo[n]
                if (mitad == valor || (mitad - valor) == 1) {//valida si cumple la regla del postulado
                    //System.out.println(contarElementos(arreglo, e) + " rachas de longitud " + c + " Se cumple");
                    model_rachas.addRow(new Object[]{contarElementos(arreglo, e)+" "
                            + " Rachas de longitud "+c+".\tSe cumple"});
                } else {
                    //System.out.println(contarElementos(arreglo, e) + " rachas de longitud " + c + " No se cumple");
                    model_rachas.addRow(new Object[]{contarElementos(arreglo, e)+" "
                            + " Rachas de longitud "+c+".\tNo se cumple"});
                    cumple = false;//El postulado se chinga
                }
                addE(auxiliar, e);//usa el arreglo aux para no repetir el conteo de rachas
            }
        }
        //System.out.println("Postulado 2: "+cumple);
        cumpleP.setText("Cumple postulado G2: "+cumple);
    }

    //Limpia el arreglo para no dejar basura
    public static  void clearA(char [] v){
        Arrays.fill(v, (char) 0);
    }
    //Agrega los elementos repetidos
    public static void addE(char[] v, int e){
        if (MAX<v.length){
            v[MAX++]= (char) e;
        }
    }
    //Cuenta los elementos repetidos
    public static int contarElementos(char[] v , int e){
        int c =0;
        for (int j : v) {
            if (j == e)
                c++;
        }
        return c;
    }
    //Busca si se encuentra el elemento
    public static boolean buscarElemento(char[] v, int e){
        for (int j : v) {
            if (j == e) return true;
        }
        return false ;
    }

   

    
    
}
