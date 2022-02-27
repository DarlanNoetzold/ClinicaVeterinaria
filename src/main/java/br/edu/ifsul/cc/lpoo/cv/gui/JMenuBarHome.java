package br.edu.ifsul.cc.lpoo.cv.gui;
import br.edu.ifsul.cc.lpoo.cv.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class JMenuBarHome extends JMenuBar implements ActionListener {

    private JMenu menuArquivo;
    private JMenuItem menuItemSair;

    private JMenu menuCadastro;
    private JMenuItem menuItemFuncionario;
    private JMenuItem menuItemConsulta;
    private JMenuItem menuItemReceita;

    private Controle controle;

    public JMenuBarHome(Controle controle){

        this.controle = controle;

        initComponents();
    }

    private void initComponents(){

        menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A);//ativa o ALT + A para acessar esse menu - acessibilidade
        menuArquivo.setToolTipText("Arquivo"); //acessibilidade
        menuArquivo.setFocusable(true); //acessibilidade


        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setToolTipText("Sair"); //acessibilidade
        menuItemSair.setFocusable(true);     //acessibilidade

        menuItemSair.addActionListener(this);
        menuItemSair.setActionCommand("menu_sair");
        menuArquivo.add(menuItemSair);

        menuCadastro = new JMenu("Cadastros");
        menuCadastro.setMnemonic(KeyEvent.VK_C);//ativa o ALT + C para acessar esse menu - acessibilidade
        menuCadastro.setToolTipText("Cadastro"); //acessibilidade
        menuCadastro.setFocusable(true); //acessibilidade

        menuItemFuncionario = new JMenuItem("Funcionario");
        menuItemFuncionario.setToolTipText("Funcionario"); //acessibilidade
        menuItemFuncionario.setFocusable(true); //acessibilidade

        menuItemFuncionario.addActionListener(this);
        menuItemFuncionario.setActionCommand("menu_funcionario");
        menuCadastro.add(menuItemFuncionario);

        menuItemConsulta = new JMenuItem("Consulta");
        menuItemConsulta.setToolTipText("Consulta"); //acessibilidade
        menuItemConsulta.setFocusable(true); //acessibilidade

        menuItemConsulta.addActionListener(this);
        menuItemConsulta.setActionCommand("menu_consulta");
        menuCadastro.add(menuItemConsulta);

        menuItemReceita = new JMenuItem("Receita");
        menuItemReceita.setToolTipText("Receita"); //acessibilidade
        menuItemReceita.setFocusable(true); //acessibilidade

        menuItemReceita.addActionListener(this);
        menuItemReceita.setActionCommand("menu_receita");
        menuCadastro.add(menuItemReceita);

        this.add(menuArquivo);
        this.add(menuCadastro);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(menuItemSair.getActionCommand())){

            //se o usuario clicou no menuitem Sair
            int d = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema? ", "Sair", JOptionPane.YES_NO_OPTION);
            if(d == 0){
                controle.fecharBD();//fecha a conexao com o banco de dados.
                System.exit(0);//finaliza o processo do programa.
            }


        }else if(e.getActionCommand().equals(menuItemFuncionario.getActionCommand())){

            //se o usuario clicou no menuitem funcionario
            controle.showTela("tela_funcionario");
        }else if(e.getActionCommand().equals(menuItemConsulta.getActionCommand())) {

            //se o usuario clicou no menuitem consulta
            controle.showTela("tela_consulta");
        }else if(e.getActionCommand().equals(menuItemReceita.getActionCommand())) {

            //se o usuario clicou no menuitem receita
            controle.showTela("tela_receita");
        }

    }
}
