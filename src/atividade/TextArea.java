package atividade;

/**
 *
 * @author Jv Loreti
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextArea extends JPanel{
    JTextArea textArea;
    
    public TextArea(){
        setLayout(new BorderLayout());
        
        //Inicializa a área de texto
        textArea = new JTextArea();
        //Implementa a área de texto
        super.add(new JScrollPane(textArea));
        //Define as dimensões da área de texto
        textArea.setPreferredSize(new Dimension(600, 100));
    }
    
    //Substitui append (escreve texto quando chamado)
    public void escreverTexto(String txt){
        textArea.append(txt);
    }
}
