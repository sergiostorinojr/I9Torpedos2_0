/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.repository;

import br.com.sergiostorino.model.service.exception.PersistenceException;
import java.util.Collection;

/**
 *
 * @author Junior
 * @param <T>
 */
public interface BaseRepositoryInterface<T> {

    /**
     *
     * @param obj
     * @throws PersistenceException
     */
    public void save(T obj) throws PersistenceException;

    /**
     *
     * @param obj
     * @return
     * @throws PersistenceException
     */
    public T update(T obj) throws PersistenceException;

    /**
     *
     * @param id
     * @throws PersistenceException
     */
    public void delete(Long id) throws PersistenceException;

    /**
     *
     * @param id
     * @return
     * @throws PersistenceException
     */
    public T findById(Long id) throws PersistenceException;

    /**
     *
     * @return @throws PersistenceException
     */
    public Collection<T> getAll() throws PersistenceException;

}
