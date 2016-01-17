/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.controller.repository;

import br.com.sergiostorino.model.service.exception.PersistenceException;
import java.util.Collection;

/**
 *
 * @author Junior
 * @param <T>
 */
public interface ControllerRepository<T> {

    public void save(T obj) throws PersistenceException;

    public T update(T obj) throws PersistenceException;

    public void toRemove(Long id) throws PersistenceException;

    public Collection<T> findall();

    public T findById(Long id);

}
