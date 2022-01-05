package br.edu.ifsul.cc.lpoo.cs.test;

import br.edu.ifsul.cc.lpoo.cv.model.*;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import org.junit.Test;

import java.util.List;

public class TestPersistenciaJDBC {

    @Test
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
    public void testListPersistenciaConsulta() throws Exception {
        PersistenciaJDBC persistencia = new PersistenciaJDBC();

        if(persistencia.conexaoAberta()){
            List<Consulta> lista = persistencia.listPesistenciaConsulta();
            if(!lista.isEmpty()){
                for(Consulta c : lista){
                    System.out.println("\n\n----Dados de consulta----\n" +
                            "\nId: " + c.getId() +
                            "\nCPF do Médico: "+c.getMedico().getCpf()+
                            "\nCRMV do Médico: "+c.getMedico().getNumero_crmv()+
                            "\nNome do Médico: "+c.getMedico().getNome()+
                            "\nId do PET: "+c.getPet().getId()+
                            "\nNome do PET: "+c.getPet().getNome()+
                            "\nReceitas desta Consulta: ");
                    c.getReceitas().stream().forEach(r -> System.out.println("Receita{" +
                            "id=" + r.getId() +
                            ", orientacao='" + r.getOrientacao()+ '\'' +
                            '}'));
                    c.getReceitas().stream().forEach(r -> {
                        try {
                            persistencia.remover(r);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    persistencia.remover(c);

                }
            }else{

            }
        }
        persistencia.fecharConexao();
    }
}