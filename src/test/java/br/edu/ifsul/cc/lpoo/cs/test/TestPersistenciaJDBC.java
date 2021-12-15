package br.edu.ifsul.cc.lpoo.cs.test;

import br.edu.ifsul.cc.lpoo.clinicaVet.model.dao.PersistenciaJDBC;
import java.util.List;
import org.junit.Test;

public class TestPersistenciaJDBC {

    //@Test
    // atividade assíncrona: 02/12/2021  implementar o test para a recuperacao de registro em tb_patente.

    //@Test
    public void testConexao() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JDBC");

            persistencia.fecharConexao();

        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }

    }

    @Test
    public void testListPersistenciaEndereco() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){

            List<Endereco> lista = persistencia.listEnderecos();

            if(!lista.isEmpty()){

                for(Endereco e : lista){

                    System.out.println("Endereco: "+e.getId()+" CEP: "+e.getCep());

                    //e.setComplemento("complementou alterado.");
                    //persistencia.persist(e);

                    persistencia.remover(e);
                }
            }else{

                System.out.println("Não encontrou o endereco");

                Endereco end = new Endereco();
                end.setCep("99010010");
                end.setComplemento("nenhum");
                persistencia.persist(end); //insert na tabela.
                System.out.println("Cadastrou o endereco "+end.getId());


                end = new Endereco();//reset com a nova instancia que é gerada aqui.
                end.setCep("99052250");
                end.setComplemento("110");

                persistencia.persist(end); //insert na tabela.
                System.out.println("Cadastrou o endereco "+end.getId());

            }

            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }


    //@Test
    public void testListPersistenciaPatente() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){

            List<Patente> lista = persistencia.listPatentes();

            if(!lista.isEmpty()){

                for(Patente p : lista){

                    System.out.println("Endereco: "+p.getId()+" CEP: "+p.getNome());

                    persistencia.remover(p);
                }

            }else{

                System.out.println("Não encontrou o patente");

                Patente p = new Patente();
                p.setNome("patente bronze");
                persistencia.persist(p); //insert na tabela.
                System.out.println("Cadastrou o Patente "+p.getId());


                persistencia.persist(p); //insert na tabela.
                System.out.println("Cadastrou o Patente "+p.getId());

            }

            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }


}