package com.exemplo.util;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CurtaMemoria {
    
    private int cont = 0;

    public int getCont() {
        return ++cont;
    }   
}
