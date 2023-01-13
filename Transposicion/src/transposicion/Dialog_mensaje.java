/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transposicion;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author marti
 */
public class Dialog_mensaje {
    //Poderosisimo metodo para mostrar un mensaje personalizado UwU
    public void  mostrar_Mensaje(String titulo, String mensaje) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | 
                        IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JTextArea ta = new JTextArea(5, 20);         
                ta.setText(mensaje);
                ta.setWrapStyleWord(true);
                ta.setLineWrap(true);
                ta.setCaretPosition(0);
                ta.setEnabled(true);
                ta.setEditable(false);
               
                JOptionPane.showMessageDialog(null, new JScrollPane(ta), titulo, 
                        JOptionPane.INFORMATION_MESSAGE);                                    
            }
        });
    }
}
