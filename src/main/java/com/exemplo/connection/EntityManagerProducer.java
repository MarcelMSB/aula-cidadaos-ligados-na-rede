package com.exemplo.connection;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {
    
    @Produces
    @PersistenceContext(unitName = "projetoExemploPU")
    private EntityManager em;
}
