
package br.edu.ifsul.cc.lpoo.cv.gui.consulta.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
import java.awt.*;

public class JPanelAConsulta extends JPanel {
    
    private CardLayout cardLayout;
    private Controle controle;
    
    private JPanelAConsultaFormulario formulario;
    private JPanelAConsultaListagem listagem;
    
    public JPanelAConsulta(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
        
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        formulario = new JPanelAConsultaFormulario(this, controle);
        listagem = new JPanelAConsultaListagem(this, controle);
        
        this.add(formulario, "tela_consulta_formulario");
        this.add(listagem, "tela_consulta_listagem");
        
        cardLayout.show(this, "tela_consulta_listagem");
    }
    
    public void showTela(String nomeTela){
        
        cardLayout.show(this, nomeTela);
    }

    public Controle getControle() {
        return controle;
    }
    
    
    
}
