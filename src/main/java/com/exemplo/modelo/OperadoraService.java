package com.exemplo.modelo;

public class OperadoraService extends AbstractService<Operadora> {

    public Operadora update(int id, Operadora operadora) {
        Operadora retorno = find(id);
        if (retorno != null) {
            retorno.setCategoria(operadora.getCategoria());
            retorno.setCodigo(operadora.getCodigo());
            retorno.setNome(operadora.getNome());
            retorno.setPreco(operadora.getPreco());
            return em.merge(retorno);
        }
        return null;
    }
}
