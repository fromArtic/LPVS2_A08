package exemplo_swing;

/**
 *
 * @author Jv Loreti
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
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
    JFileChooser escolhaArquivo;
    
    public Frame(){
        //Define as dimensões
        super.setSize(900, 500);
        
        //Define o layout
        super.setLayout(new BorderLayout());
        
        //Chama o método criarMenu
        super.setJMenuBar(criarMenu());
        //Inicializa o JFileChooser escolhaArquivo
        escolhaArquivo = new JFileChooser();
        
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
            public void enviarFormulario(String n, String o, String i, String s, boolean estr, String p, String g){
                if(!n.isEmpty() && !o.isEmpty()){ //Valida se os campos do formulário não estão vazios
                    textArea.escreverTexto(n + " - " + i + " - " +  g + " - " + s + " - " + o + " - " + p + "\n");
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
        
        JMenu arquivo = new JMenu("Arquivo");
        JMenuItem importarArquivo = new JMenuItem("Importar arquivo ...");
        JMenuItem exportarArquivo = new JMenuItem("Exportar arquivo ...");
        JMenuItem sair = new JMenuItem("Sair");
        arquivo.add(importarArquivo);
        arquivo.add(exportarArquivo);
        arquivo.addSeparator(); //Linha de separação
        arquivo.add(sair);
        
        //Importar arquivo
        importarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showOpenDialog(Frame.this);
                
                if(ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                }
            }
        });
        
        //Exportar arquivo
        exportarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showSaveDialog(Frame.this);
                
                if(ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                }
            }
        });
        
        //Encerra o programa
        sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = JOptionPane.showConfirmDialog(Frame.this, "Realmente deseja sair?", "Confirmação de saída", JOptionPane.OK_CANCEL_OPTION);
                
                if(ret == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
        
        JMenu janela = new JMenu("Janela");
        JMenu exibir = new JMenu("Exibir");
        JCheckBoxMenuItem chkFormulario = new JCheckBoxMenuItem("Formulário");
        janela.add(exibir);
        exibir.add(chkFormulario);
        chkFormulario.setSelected(true);
        
        //Exibe ou oculta a área de formulário
        chkFormulario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JCheckBoxMenuItem chk = (JCheckBoxMenuItem) e.getSource();
                areaDeFormulario.setVisible(chk.isSelected());
            }
        });
        
        //Determina as teclas de atalho
        arquivo.setMnemonic(KeyEvent.VK_A);
        sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        chkFormulario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        
        menuBar.add(arquivo);
        menuBar.add(janela);
        
        return menuBar;
    }
}
