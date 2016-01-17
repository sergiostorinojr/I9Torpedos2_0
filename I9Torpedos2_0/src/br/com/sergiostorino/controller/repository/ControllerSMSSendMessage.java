/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.controller.repository;

import br.com.sergiostorino.model.service.exception.PersistenceException;
import br.com.sergiostorino.model.service.repository.BaseRepository;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 * @param <T>
 */
public class ControllerSMSSendMessage<T> implements ControllerRepository<T> {

    private Class<?> clazz;

    public ControllerSMSSendMessage(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T obj) throws PersistenceException {
        new BaseRepository<T>(clazz).save(obj);
    }

    @Override
    public T update(T obj) throws PersistenceException {
        return new BaseRepository<T>(clazz).update(obj);
    }

    @Override
    public void toRemove(Long id) throws PersistenceException {
        new BaseRepository<T>(clazz).delete(id);
    }

    @Override
    public Collection<T> findall() {
        return null;
    }

    @Override
    public T findById(Long id) {
        T findById = null;
        try {
            findById = new BaseRepository<T>(clazz).findById(id);
            return findById;
        } catch (PersistenceException ex) {
            Logger.getLogger(ControllerSMSSendMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return findById;
    }

    /**
     * @return the clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

}
