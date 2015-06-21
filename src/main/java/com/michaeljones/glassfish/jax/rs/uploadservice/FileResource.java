/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaeljones.glassfish.jax.rs.uploadservice;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author michaeljones
 */
public class FileResource {

    private String filename;

    /**
     * Creates a new instance of FileResource
     */
    private FileResource(String filename) {
        this.filename = filename;
    }

    /**
     * Get instance of the FileResource
     */
    public static FileResource getInstance(String filename) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of FileResource class.
        return new FileResource(filename);
    }

    /**
     * Retrieves representation of an instance of com.michaeljones.glassfish.jax.rs.uploadserver.FileResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }


    /**
     * DELETE method for resource FileResource
     */
    @DELETE
    public void delete() {
    }
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    @PUT
    @Consumes(value = MediaType.APPLICATION_OCTET_STREAM)
    public void putOctetStream(@Suspended final AsyncResponse asyncResponse, @Context final HttpServletRequest request) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                doPutOctetStream(request);
                asyncResponse.resume(javax.ws.rs.core.Response.ok().build());
            }
        });
    }

    private void doPutOctetStream(@Context final HttpServletRequest request) {
        try {
            request.getContentType();
            String method = request.getMethod();
            ServletInputStream inputStream = request.getInputStream();
            byte[] b = new byte[1024];
            int count = 0;
            while (true) {
                int bytes = inputStream.read(b);
                System.out.println("Stream read: " + count);
                if (bytes < 0) {
                    break;
                }
                count += bytes;
            }
            
            System.out.println("Total bytes from stream: " + count);
        } catch (IOException ex) {
            Logger.getLogger(FileResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
