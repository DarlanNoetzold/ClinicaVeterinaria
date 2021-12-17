package br.edu.ifsul.cc.lpoo.cv.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenciaJPA implements InterfacePersistencia {

    public EntityManagerFactory factory;
    public EntityManager entity;

    public PersistenciaJPA(){
        factory = Persistence.createEntityManagerFactory("clinica_vet_db");
        entity = factory.createEntityManager();
    }


    @Override
    public Boolean conexaoAberta() {

        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {

        entity.close();
    }

    @Override
    public Object find(Class c, Object id) throws Exception {

        return entity.find(c, id);
    }

    @Override
    public void persist(Object o) throws Exception {

        entity.getTransaction().begin();
        entity.persist(o);
        entity.getTransaction().commit();

    }

    @Override
    public void remover(Object o) throws Exception {

        entity.getTransaction().begin();
        entity.remove(o);
        entity.getTransaction().commit();

    }

}