/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.repository;

import br.com.sergiostorino.model.service.exception.PersistenceException;
import br.com.sergiostorino.model.service.util.jpa.EntityManagerUtil;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Classe responsável pela persistencia dos objetos com as funções basicas
 * Gravar, Atualizar, Excluir, Buscar por Id, Buscar Todos. Classe Generica
 *
 * @author Junior
 * @param <T>
 */
public class BaseRepository<T> implements BaseRepositoryInterface<T> {

    private EntityManager em;
    private final Class<?> clazz;

    public BaseRepository(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T obj) throws PersistenceException {
        boolean status = false;
        try {
            //if (this.em != null && !this.em.getTransaction().isActive()) {
                this.em = EntityManagerUtil.getEntityManager();
                this.em.getTransaction().begin();
                
            //}

            if (obj != null) {
                this.em.persist(obj);

                this.em.getTransaction().commit();
            }
        } finally {

            //this.em.close();
        }

    }

    @Override
    public T update(T obj) throws PersistenceException {
        try {
            if (this.em != null && !this.em.getTransaction().isActive()) {
                this.em = EntityManagerUtil.getEntityManager();
            }
            this.em.getTransaction().begin();

            if (obj != null) {
                obj = this.em.merge(obj);

                this.em.getTransaction().commit();
            }
        } finally {
            this.em.close();
        }
        return obj;

    }

    @Override
    public void delete(Long id) throws PersistenceException {
        try {
            if (this.em != null && !this.em.getTransaction().isActive()) {
                this.em = EntityManagerUtil.getEntityManager();
            }
            this.em.getTransaction().begin();

            if (id != null) {
                this.em.remove(id);

                this.em.getTransaction().commit();
            }
        } finally {
            this.em.close();
        }

    }

    @Override
    public T findById(Long id) throws PersistenceException {
        return EntityManagerUtil.getEntityManager().find((Class<T>) clazz, id);
    }

    @Override
    public Collection<T> getAll() throws PersistenceException {
        TypedQuery<?> query;
        try {

            query = EntityManagerUtil.getEntityManager().createQuery(" FROM ", clazz);

        } finally {
            this.em.close();
        }
        return (Collection<T>) query.getResultList();

    }

    /**
     * @return the clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }

}
