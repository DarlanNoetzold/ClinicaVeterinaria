package br.edu.ifsul.cc.lpoo.cv;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import br.edu.ifsul.cc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.cv.gui.JMenuBarHome;
import br.edu.ifsul.cc.lpoo.cv.gui.JPanelHome;
import br.edu.ifsul.cc.lpoo.cv.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import javax.swing.JOptionPane;

public class Controle {

    private PersistenciaJDBC conexaoJDBC;

    private JFramePrincipal frame; // frame principal da minha aplicação gráfica

    private JPanelAutenticacao pnlAutenticacao;

    private JMenuBarHome menuBar; //menu principal

    private JPanelHome pnlHome; // paine de boas vindas (home)


    //construtor.
    public Controle(){

    }

    public boolean conectarBD() throws Exception {

        conexaoJDBC = new PersistenciaJDBC();

        if(conexaoJDBC!= null){

            return conexaoJDBC.conexaoAberta();
        }

        return false;
    }

    public void fecharBD(){

        System.out.println("Fechando conexao com o Banco de Dados");
        conexaoJDBC.fecharConexao();

    }

    public void initComponents(){


        //inicia a interface gráfica.
        //"caminho feliz" : passo 5

        frame = new JFramePrincipal();



        pnlAutenticacao = new JPanelAutenticacao(this);

        menuBar = new JMenuBarHome(this);

        pnlHome = new JPanelHome(this);

        frame.addTela(pnlAutenticacao, "tela_autenticacao");//carta 1
        frame.addTela(pnlHome, "tela_home");//carta 2

        frame.showTela("tela_autenticacao"); // apreseta a carta cujo nome é "tela_autenticacao"

        frame.setVisible(true); // torna visível o jframe


    }

    public void autenticar(String cpf, String senha) {

        try{

            Pessoa j =  conexaoJDBC.doLogin(cpf, senha);

            if(j != null || (j.getCpf() != null && j.getSenha() != null)){
                if(j.getCpf() != null) {
                    if(j.getSenha() != null) {
                        JOptionPane.showMessageDialog(pnlAutenticacao, "Pessoa " + j.getCpf() + " autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                        frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                        frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)
                    }else{
                        JOptionPane.showMessageDialog(pnlAutenticacao, "Senha inválida!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(pnlAutenticacao, "CPF inválido!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void showTela(String nomeTela){


        frame.showTela(nomeTela);
    }



}
