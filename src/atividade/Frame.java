package atividade;

/**
 *
 * @author Jv Loreti
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

public class Frame extends JFrame{
    TextArea textArea;
    AreaDeFormulario areaDeFormulario;
    
    public Frame(){
        //Define as dimensões
        super.setSize(1200, 500);
        
        //Define o layout
        super.setLayout(new BorderLayout());
        
        //Chama o método criarMenu
        super.setJMenuBar(criarMenu());
        
        //Inicializa a área de texto
        textArea = new TextArea();
        //Define a posição da área de texto
        super.add(textArea, BorderLayout.EAST);
        
        //Inicializa a área de formulário
        areaDeFormulario = new AreaDeFormulario();
        //Implementa a área de formulário
        super.add(areaDeFormulario);
        
        //Comunicação entre a área de formulário e de texto
        areaDeFormulario.setFormularioListener(new FormularioListener(){
            @Override
            public void enviarFormulario(String nome, String endereco, String cidade, String estado, String sexo, boolean LPVS2, boolean POOS3, boolean DSIS4){
                if(!nome.isEmpty() && !endereco.isEmpty() && !cidade.isEmpty() && !estado.isEmpty() && !sexo.isEmpty() 
                        && LPVS2 != false || POOS3 != false || DSIS4 != false){ //Valida se os campos do formulário não estão vazios
                    textArea.escreverTexto("Nome: " + nome + "\n" +
                                           "Endereço: " + endereco + "\n" +
                                           "Cidade: " +  cidade +
                                           ", " + estado + "\n" +
                                           "Sexo: " + sexo);
                                            if(LPVS2 == true){
                                                textArea.escreverTexto("\nMatriculado(a) em LPVS2.");
                                            }
                                            if(POOS3 == true){
                                                textArea.escreverTexto("\nMatriculado(a) em POOS3.");
                                            }
                                            if(DSIS4 == true){
                                                textArea.escreverTexto("\nMatriculado(a) em DSIS4.");
                                            }
                                           textArea.escreverTexto("\n\n");
                }
            }
        });
        
        //Exibe o JFrame
        super.setVisible(true);
        //Encerra a operação ao fechar o JFrame
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    //Retorna a barra de menu
    private JMenuBar criarMenu(){
        JMenuBar menuBar = new JMenuBar();
        
        //Menu Arquivo
        JMenu arquivo = new JMenu("Arquivo");
        JMenu exibir = new JMenu("Exibir");
        JMenuItem sair = new JMenuItem("Sair");
        arquivo.add(exibir);
        arquivo.add(sair);
        
        //Exibe ou oculta a área de texto
        JCheckBoxMenuItem chkTextArea = new JCheckBoxMenuItem();
        exibir.add(chkTextArea);
        chkTextArea.setSelected(true);
        chkTextArea.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JCheckBoxMenuItem chk = (JCheckBoxMenuItem) e.getSource();
                textArea.setVisible(chk.isSelected());
            }
        });
        
        //Confirmação de encerramento do programa
        sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = JOptionPane.showConfirmDialog(Frame.this, "Encerrar operação?", "Confirmação de encerramento", JOptionPane.YES_NO_OPTION);
                
                if(ret == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        
        //Teclas de atalho
        arquivo.setMnemonic(KeyEvent.VK_A);
        sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        chkTextArea.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        
        menuBar.add(arquivo);
        
        return menuBar;
    }
}
