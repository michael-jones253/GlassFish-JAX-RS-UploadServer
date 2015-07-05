/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaeljones.glassfish.jax.rs.uploadserver;

import com.michaeljones.glassfish.jax.rs.uploadservice.MountRequestFilter;
import com.michaeljones.glassfish.jax.rs.uploadservice.devnull.DevNullMount;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author michaeljones
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    
    private final DevNullMount devNull;

    public ApplicationConfig() {
        this.devNull = new DevNullMount();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        
        // This is where we add all the mounts that this upload server writes files to.
        MountRequestFilter.AddMount("devnull", devNull);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.michaeljones.glassfish.jax.rs.uploadservice.FileResource.class);
        resources.add(com.michaeljones.glassfish.jax.rs.uploadservice.FileSystem.class);
        resources.add(com.michaeljones.glassfish.jax.rs.uploadservice.MountRequestFilter.class);
    }
    
}
