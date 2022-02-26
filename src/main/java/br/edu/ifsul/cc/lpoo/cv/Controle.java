package br.edu.ifsul.cc.lpoo.cv;
import br.edu.ifsul.cc.lpoo.cv.gui.consulta.acessibilidade.JPanelAConsulta;
import br.edu.ifsul.cc.lpoo.cv.gui.funcionario.acessibilidade.JPanelAFuncionario;
import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
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

    private JPanelAutenticacao pnlAutenticacao; //painel para a autenticacao do Funcionario.

    private JMenuBarHome menuBar; //menu principal

    private JPanelHome pnlHome; // painel de boas vindas (home)

    private JPanelAFuncionario pnlAFuncionario; // painel de manutencao para funcionario.

    private JPanelAConsulta pnlAConsulta;
    //construtor.
    public Controle(){

    }

    public boolean conectarBD() throws Exception {

        conexaoJDBC = new PersistenciaJDBC();

        if(getConexaoJDBC()!= null){

            return getConexaoJDBC().conexaoAberta();
        }

        return false;
    }

    public void fecharBD(){

        System.out.println("Fechando conexao com o Banco de Dados");
        getConexaoJDBC().fecharConexao();

    }

    public void initComponents(){

        frame = new JFramePrincipal();

        pnlAutenticacao = new JPanelAutenticacao(this);

        menuBar = new JMenuBarHome(this);

        pnlHome = new JPanelHome(this);

        pnlAFuncionario = new JPanelAFuncionario(this);

        pnlAConsulta = new JPanelAConsulta(this);

        frame.addTela(pnlAutenticacao, "tela_autenticacao");
        frame.addTela(pnlHome, "tela_home");
        frame.addTela(pnlAFuncionario, "tela_funcionario");
        frame.addTela(pnlAConsulta, "tela_consulta");

        frame.showTela("tela_autenticacao"); // apreseta a carta cujo nome é "tela_autenticacao"

        frame.setVisible(true); // torna visível o jframe


    }

    public void autenticar(String cpf, String senha) {

        try{

            Pessoa j =  getConexaoJDBC().doLogin(cpf, senha);

            if(j != null){

                JOptionPane.showMessageDialog(pnlAutenticacao, "Funcionario "+j.getCpf()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)

            }else{

                JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cadastrar(Object o){
        try {
            if (o instanceof Pessoa) {
                getConexaoJDBC().persist(o);
            }else if(o instanceof Funcionario){
                getConexaoJDBC().persist(o);
            }
        }catch (Exception e)   {
            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar o cadastro no Banco de Dados!", "Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showTela(String nomeTela){

        if(nomeTela.equals("tela_autenticacao")){

            pnlAutenticacao.cleanForm();
            frame.showTela(nomeTela);
            pnlAutenticacao.requestFocus();

        }else if(nomeTela.equals("tela_funcionario")){
            pnlAFuncionario.showTela("tela_funcionario_listagem");
            frame.showTela(nomeTela);
        }else if(nomeTela.equals("tela_consulta")){
            pnlAFuncionario.showTela("tela_consulta_listagem");
            frame.showTela(nomeTela);
        }

    }

    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
}
