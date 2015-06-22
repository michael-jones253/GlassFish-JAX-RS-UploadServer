/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaeljones.glassfish.jax.rs.uploadservice;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.UriBuilder;

/**
 * Change the incoming URI to point to our file system URI.
 * @author michaeljones
 */
@Provider
@PreMatching
public class MountRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {        
        URI baseUri = crc.getUriInfo().getBaseUri();
        
        URI uri = crc.getUriInfo().getRequestUri();
        System.out.println("Original URI: " + uri.toString());
        
        UriBuilder mountRedirectUri = UriBuilder.fromUri(uri);

        List<PathSegment> pathSegments = crc.getUriInfo().getPathSegments();
        String[] redirectSegments = new String[pathSegments.size()];
        
        int pathIndex = 0;
        for (PathSegment segment: pathSegments) {            
            System.out.println("Path segment: " + segment.getPath());
            
            // Change the first path segment to point to our filesystem URI.
            // FIX ME filter on what is allowed to be changed and capture the mount type.
            redirectSegments[pathIndex] = pathIndex == 0 ? "fs" : segment.getPath();
            pathIndex++;
        }

        // Replace old path with new redirected path segments.
        mountRedirectUri.replacePath(baseUri.getPath()).segment(redirectSegments);
        System.out.println("Rebuilt URI: " + mountRedirectUri.build().toString());
        
        crc.setRequestUri(mountRedirectUri.build());
        crc.setProperty("mprop", "hello world");
    }

}
