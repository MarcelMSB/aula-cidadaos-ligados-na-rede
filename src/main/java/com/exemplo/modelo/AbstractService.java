package com.exemplo.modelo;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;

public abstract class AbstractService<T> {

    @Inject
    protected EntityManager em;
    protected Class<T> type;

    @PostConstruct
    private void init() {
        this.type = getMappedSuperclass(getClass());
    }

    public T create(@Valid T entity) {
        return em.merge(entity);
    }

    public T find(int id) {
        T retorno = em.find(type, id);
        return retorno;
    }
    
    public List<T> findAll() {
        String consulta = "SELECT e FROM " + type.getName() + " e";
        Query query = em.createQuery(consulta);
        return query.getResultList();
    }
    
    public boolean delete(int id) {
        T retorno = find(id);
        if (retorno != null) {
            em.remove(retorno);
            return true;
        }
        return false;
    }
    
    public static Class getMappedSuperclass(final Class<?> clazz) {
        return getMappedSuperclass(clazz, 0);
    }

    public static Class getMappedSuperclass(final Class<?> clazz, int arg) {
        if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
            return (Class) genericSuperclass.getActualTypeArguments()[arg];
        }
        return null;
    }
}
