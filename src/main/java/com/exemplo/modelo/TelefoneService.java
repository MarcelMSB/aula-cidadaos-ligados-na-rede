package com.exemplo.modelo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class TelefoneService extends AbstractService<Telefone> {

    public List<Telefone> findAll(int limit) {
        String consulta = "SELECT t FROM Telefone t";
        Query query = em.createQuery(consulta);
        query.setMaxResults(limit < 5 ? 5 : limit);
        return query.getResultList();
    }

    public List<Telefone> findByNome(final String nome) {
        String consulta = "SELECT t FROM Telefone t WHERE t.nome = :nome";
        Query query = em.createQuery(consulta);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public List<Telefone> findByOperado(int idOperadora) {
        String consulta = "SELECT t FROM Telefone t WHERE t.operadora.id = :idOperadora";
        Query query = em.createQuery(consulta);
        query.setParameter("idOperadora", idOperadora);
        return query.getResultList();
    }

    public Telefone update(int id, Telefone telefone) {
        Telefone retorno = find(id);
        if (retorno != null) {
            retorno.setDdd(telefone.getDdd());
            retorno.setNumero(telefone.getNumero());
            retorno.setNome(telefone.getNome());
            retorno.setOperadora(telefone.getOperadora());
            return em.merge(retorno);
        }
        return null;
    }
}
