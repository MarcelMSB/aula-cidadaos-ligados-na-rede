package com.exemplo.application;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.exemplo.resource.Atividade01Resource.class);
        resources.add(com.exemplo.resource.Atividade02Resource.class);
        resources.add(com.exemplo.resource.Atividade03Resource.class);
        resources.add(com.exemplo.resource.Atividade04Resource.class);
        resources.add(com.exemplo.resource.CalculoResource.class);
        resources.add(com.exemplo.resource.MyApplicationExceptionHandler.class);
        resources.add(com.exemplo.resource.OperadoraResource.class);
        resources.add(com.exemplo.resource.TelefoneResource.class);
        resources.add(com.exemplo.resource.TelefoneResourceV1.class);
        resources.add(com.exemplo.resource.TelefoneResourceV2.class);
    }
    
}
