
package br.edu.ifsul.cc.lpoo.cv.gui.receita.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
import java.awt.*;

public class JPanelAReceita extends JPanel {

    private CardLayout cardLayout;
    private Controle controle;

    private JPanelAReceitaFormulario formulario;
    private JPanelAReceitaListagem listagem;

    public JPanelAReceita(Controle controle){

        this.controle = controle;
        initComponents();
    }

    private void initComponents(){

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        formulario = new JPanelAReceitaFormulario(this, controle);
        listagem = new JPanelAReceitaListagem(this, controle);

        this.add(formulario, "tela_receita_formulario");
        this.add(listagem, "tela_receita_listagem");
        listagem.populaTable();
        cardLayout.show(this, "tela_receita_listagem");
    }

    public void showTela(String nomeTela){
        if(nomeTela.equals("tela_receita_listagem")){
            System.out.println("testeeee");
            listagem.populaTable();
        }else if(nomeTela.equals("tela_receita_formulario")){

            getFormulario().populaComboMedico();
            getFormulario().populaComboPet();
        }
        cardLayout.show(this, nomeTela);
    }

    public Controle getControle() {
        return controle;
    }

    public JPanelAReceitaFormulario getFormulario() {
        return formulario;
    }


}
