package br.edu.ifsul.cc.lpoo.cs.test;

import br.edu.ifsul.cc.lpoo.clinicaVet.model.dao.PersistenciaJPA;
import java.util.List;
import org.junit.Test;

public class TestPersistenciaJPA {

    @Test
    public void testConexaoGeracaoTabelas(){

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");

            persistencia.fecharConexao();

        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

    //@Test
    public void testListPersistenciaEndereco() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){

            List<Endereco> lista = persistencia.listEnderecos();

            if(!lista.isEmpty()){

                for(Endereco e : lista){

                    System.out.println("Endereco: "+e.getId()+" CEP: "+e.getCep());
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
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
    }


    //@Test
    public void testPersistenciaEndereco() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");

            //casting (modelo o objet retornado pelo find em Endereco)
            Endereco end = (Endereco) persistencia.find(Endereco.class, 6);

            if(end == null){

                System.out.println("Não encontrou o endereco");

                end = new Endereco();
                end.setCep("99010000");
                end.setComplemento("00");

                System.out.println("Endereço : "+end.getId());

                persistencia.persist(end); //insert na tabela.

                System.out.println("Cadastro o endereço : "+end.getId());

            }else{

                System.out.println("Encontrou o endereco: "+end.getId());

                end.setComplemento("11000");
                persistencia.persist(end);//upate.

                System.out.println("Alterou o endereco: "+end.getId());

                //persistencia.remover(end);
                //System.out.println("Removeu o endereco: "+end.getId());
            }

            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

    //@Test
    public void testPersistenciaArtefato() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){

            Arma a = (Arma) persistencia.find(Arma.class, 5);

            if(a == null){

                a = new Arma();
                a.setComprimento_cano(0.2F);
                a.setNome("Revolver 22");
                a.setPeso(1.7F);
                a.setTipo(Tipo.FOGO);
                a.setValor(1500.00F);
                a.setMunicao((Municao) persistencia.find(Municao.class, 4));
                persistencia.persist(a);
            }else{

                System.out.println("Arma encontrada "+a.getId());
                for(Municao m : a.getMunicoes()){

                    System.out.println("    Municao "+m.getNome());
                }

            }

            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
    }

    //@Test
    public void testPersistenciaMunicao() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){

            Municao m = (Municao) persistencia.find(Municao.class, 1);

            if(m == null){

                m = new Municao();
                m.setNome("Bala de festim");
                m.setPeso(1.7F);
                m.setValor(1500.00F);
                m.setExplosiva(Boolean.FALSE);
                m.setCalibre(Calibre.C03);
                persistencia.persist(m);
            }else{

                System.out.println("Municao encontrada "+m.getId());

            }

            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
    }

}