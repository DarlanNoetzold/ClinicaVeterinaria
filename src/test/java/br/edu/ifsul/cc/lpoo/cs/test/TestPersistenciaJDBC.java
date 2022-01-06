package br.edu.ifsul.cc.lpoo.cs.test;

import br.edu.ifsul.cc.lpoo.cv.model.*;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import org.junit.Test;

import java.util.Calendar;
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
                System.out.println("Nenhum dado encontrado, inciando incersão!");
                Pessoa pessoaMedico = new Pessoa();
                pessoaMedico.setTipo("M");
                pessoaMedico.setCep("45678912");
                pessoaMedico.setComplemento("Eh isso ai mesmo");
                pessoaMedico.setNome("João");
                pessoaMedico.setCpf("00000000000");
                pessoaMedico.setData_nascimento(Calendar.getInstance());
                pessoaMedico.setEmail("medico@mail.com");
                pessoaMedico.setEndereco("Algum lugar, numero 0, Rua");
                pessoaMedico.setNumero_celular("5499999999");
                pessoaMedico.setRg("55555555555");
                pessoaMedico.setSenha("123456");

                persistencia.persist(pessoaMedico);

                Pessoa pessoaCliente = new Pessoa();
                pessoaCliente.setTipo("C");
                pessoaCliente.setCep("45678912");
                pessoaCliente.setComplemento("Eh isso ai mesmo");
                pessoaCliente.setNome("Maria");
                pessoaCliente.setCpf("11111111111");
                pessoaCliente.setData_nascimento(Calendar.getInstance());
                pessoaCliente.setEmail("medico@mail.com");
                pessoaCliente.setEndereco("Algum lugar, numero 0, Rua");
                pessoaCliente.setNumero_celular("5499999999");
                pessoaCliente.setRg("55555555555");
                pessoaCliente.setSenha("123456");

                persistencia.persist(pessoaCliente);

                Cliente cliente = new Cliente();
                cliente.setCpf("11111111111");
                cliente.setData_ultima_visita(Calendar.getInstance());

                persistencia.persist(pessoaCliente);


            }
        }
        persistencia.fecharConexao();
    }
}