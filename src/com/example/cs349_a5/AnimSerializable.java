package com.example.cs349_a5;


import java.io.IOException;

/**
*
* @author sandy
*/
public interface AnimSerializable {
    public void serialize(AnimSerializer json) throws IOException;
}