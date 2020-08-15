package com.exemplo.util;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BancoResultado {

    private List<Integer> banco = new ArrayList<>();

    public void addResultado(int resultado){
        banco.add(resultado);
    }
    
    public List<Integer> getBanco() {
        return banco;
    }
}
