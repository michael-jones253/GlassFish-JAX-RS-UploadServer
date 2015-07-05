/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaeljones.glassfish.jax.rs.uploadservice;

/**
 *
 * @author michaeljones
 */
public interface MountInterface {
    Object GetHandleForWrite(String path);
    
    /**
     *
     * @param handle
     * @param buffer
     * @param length
     */
    void WriteAllBytes(Object handle, byte[] buffer, int length);
    
    void CloseHandle(Object handle);
}
