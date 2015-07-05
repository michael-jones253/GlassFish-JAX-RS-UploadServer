/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaeljones.glassfish.jax.rs.uploadservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author michaeljones
 */
@Path("/fs")
public class FileSystem {

    @Context
    private UriInfo context;
    
    @Context
    private ContainerRequestContext containerRequestContext;

    /**
     * Creates a new instance of FileSystem
     */
    public FileSystem() {
        System.out.println("Creating FileSystem");
    }

    /**
     * Retrieves representation of an instance of com.michaeljones.glassfish.jax.rs.uploadserver.FileSystem
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //FIX ME return proper representation object for files list.
        String fsFormat = "{\"mount\":\"%1s\",\"files\":[]}";
        return String.format(fsFormat, context.getPath());
    }

    /**
     * Sub-resource locator method for {fileUri}
     */
    @Path("{fileUri}")
    public FileResource getFileResource(@PathParam("fileUri") String filename, @Context HttpServletRequest request) {
        Object myProp = this.containerRequestContext.getProperty("mprop");
        if (myProp == null) {
            throw new WebApplicationException("must be michael", Response.Status.NOT_FOUND);
        }
        
        System.out.println("Retrieved property: " + myProp.toString());
        
        return FileResource.getInstance(filename);
    }
}
