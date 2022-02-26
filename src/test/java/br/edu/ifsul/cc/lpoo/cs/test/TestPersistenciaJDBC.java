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
                Pet pet = (Pet) persistencia.find(Pet.class, persistencia.ultimoId(Pet.class));
                persistencia.remover(pet);
                Raca rac = (Raca) persistencia.find(Raca.class, persistencia.ultimoId(Raca.class));
                persistencia.remover(rac);
                Especie esp = (Especie) persistencia.find(Especie.class, persistencia.ultimoId(Especie.class));
                persistencia.remover(esp);
                Cliente cli = (Cliente) persistencia.find(Cliente.class, persistencia.ultimoId(Cliente.class));
                persistencia.remover(cli);
                Medico med = (Medico) persistencia.find(Medico.class, persistencia.ultimoId(Medico.class));
                persistencia.remover(med);


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
                cliente.setCpf(pessoaCliente.getCpf());
                cliente.setData_ultima_visita(Calendar.getInstance());

                persistencia.persist(cliente);

                Medico medico = new Medico();
                medico.setNumero_crmv("123456789");
                medico.setCpf(pessoaMedico.getCpf());

                persistencia.persist(medico);

                Especie especie = new Especie();
                especie.setNome("Cachorro");

                persistencia.persist(especie);

                Raca raca = new Raca();
                raca.setNome("Poodle");
                raca.setEspecie(((Especie) persistencia.find(Especie.class, persistencia.ultimoId(Especie.class))));


                persistencia.persist(raca);

                Pet pet = new Pet();
                pet.setRaca((Raca) persistencia.find(Raca.class, persistencia.ultimoId(Raca.class)));
                Calendar data_nasc = Calendar.getInstance();
                data_nasc.set(2015,10,25);
                pet.setData_nascimento(data_nasc);
                pet.setCliente((Cliente) persistencia.find(Cliente.class, persistencia.ultimoId(Cliente.class)));
                pet.setObservacao("Esquema vacinal");
                pet.setNome("Galadriel");

                persistencia.persist(pet);

                //Consulta 01
                Consulta consulta1 = new Consulta();
                consulta1.setMedico((Medico) persistencia.find(Medico.class, persistencia.ultimoId(Medico.class)));
                consulta1.setPet((Pet) persistencia.find(Pet.class, persistencia.ultimoId(Pet.class)));
                persistencia.persist(consulta1);

                //Consulta 02
                Consulta consulta2 = new Consulta();
                consulta2.setMedico((Medico) persistencia.find(Medico.class, persistencia.ultimoId(Medico.class)));
                consulta2.setPet((Pet) persistencia.find(Pet.class, persistencia.ultimoId(Pet.class)));
                persistencia.persist(consulta2);

                //Receitas da consulta 01
                consulta1.setId(1);
                Receita receita1 = new Receita();
                receita1.setConsulta(consulta1);
                receita1.setOrientacao("Vacina 01");
                persistencia.persist(receita1);
                Receita receita2 = new Receita();
                receita2.setConsulta(consulta1);
                receita2.setOrientacao("Vacina 02");
                persistencia.persist(receita2);
                Receita receita3 = new Receita();
                receita3.setConsulta(consulta1);
                receita3.setOrientacao("Vacina 03");
                persistencia.persist(receita3);
                Receita receita4 = new Receita();
                receita4.setConsulta(consulta1);
                receita4.setOrientacao("Vacina 04");
                persistencia.persist(receita4);

                //Receitas da consulta 02
                Receita receita5 = new Receita();
                receita5.setConsulta((Consulta) persistencia.find(Consulta.class, persistencia.ultimoId(Consulta.class)));
                receita5.setOrientacao("Anti vermes 01");
                persistencia.persist(receita5);
                Receita receita6 = new Receita();
                receita6.setConsulta((Consulta) persistencia.find(Consulta.class, persistencia.ultimoId(Consulta.class)));
                receita6.setOrientacao("Anti vermes 02");
                persistencia.persist(receita6);
                Receita receita7 = new Receita();
                receita7.setConsulta((Consulta) persistencia.find(Consulta.class, persistencia.ultimoId(Consulta.class)));
                receita7.setOrientacao("Anti vermes 03");
                persistencia.persist(receita7);



            }
        }
        persistencia.fecharConexao();
    }

    @Test
    public void testInsertFuncionario() throws Exception{
        PersistenciaJDBC persistencia = new PersistenciaJDBC();

        Pessoa pessoaFunc = new Pessoa();
        pessoaFunc.setTipo("F");
        pessoaFunc.setCep("45678912");
        pessoaFunc.setComplemento("Eh isso ai mesmo");
        pessoaFunc.setNome("Bruno");
        pessoaFunc.setCpf("123456");
        pessoaFunc.setData_nascimento(Calendar.getInstance());
        pessoaFunc.setEmail("funcionario@mail.com");
        pessoaFunc.setEndereco("Algum lugar, numero 0, Rua");
        pessoaFunc.setNumero_celular("5499999999");
        pessoaFunc.setRg("55555555555");
        pessoaFunc.setSenha("123456");

        persistencia.persist(pessoaFunc);

        Funcionario func = new Funcionario();
        func.setCargo(Cargo.valueOf("ATENDENTE"));
        func.setNumero_ctps("123456789");
        func.setNumero_pis("123456789");
        func.setCpf(pessoaFunc.getCpf());

        persistencia.persist(func);

    }

    @Test
    public void testInsertAux() throws Exception{
        PersistenciaJDBC persistencia = new PersistenciaJDBC();

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
        cliente.setCpf(pessoaCliente.getCpf());
        cliente.setData_ultima_visita(Calendar.getInstance());

        persistencia.persist(cliente);

        Medico medico = new Medico();
        medico.setNumero_crmv("123456789");
        medico.setCpf(pessoaMedico.getCpf());

        persistencia.persist(medico);

        Especie especie = new Especie();
        especie.setNome("Cachorro");

        persistencia.persist(especie);

        Raca raca = new Raca();
        raca.setNome("Poodle");
        raca.setEspecie(((Especie) persistencia.find(Especie.class, persistencia.ultimoId(Especie.class))));


        persistencia.persist(raca);

        Pet pet = new Pet();
        pet.setRaca((Raca) persistencia.find(Raca.class, persistencia.ultimoId(Raca.class)));
        Calendar data_nasc = Calendar.getInstance();
        data_nasc.set(2015,10,25);
        pet.setData_nascimento(data_nasc);
        pet.setCliente((Cliente) persistencia.find(Cliente.class, persistencia.ultimoId(Cliente.class)));
        pet.setObservacao("Esquema vacinal");
        pet.setNome("Galadriel");

        persistencia.persist(pet);
    }

}