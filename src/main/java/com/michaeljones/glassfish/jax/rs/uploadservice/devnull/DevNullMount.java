/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaeljones.glassfish.jax.rs.uploadservice.devnull;

import com.michaeljones.glassfish.jax.rs.uploadservice.MountInterface;

/**
 * The dev null mount does not put the octet stream anywhere physical. It just
 * prints out how many bytes are being streamed and is a dummy test file system.
 * @author michaeljones
 */
public class DevNullMount implements MountInterface {

    @Override
    public Object GetHandleForWrite(String path) {
        return null;
    }

    @Override
    public void WriteAllBytes(Object handle, byte[] buffer, int length) {
        System.out.println("Dev null, writing bytes from buffer capacity: " + buffer.length
                + " length: " + length);
    }

    @Override
    public void CloseHandle(Object handle) {
    }   
}
