package com.exemplo.util;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LongaMemoria {
    
    private int cont = 0;

    public int getCont() {
        return ++cont;
    }   
}
