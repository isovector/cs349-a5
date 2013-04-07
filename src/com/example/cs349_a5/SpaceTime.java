package com.example.cs349_a5;

import java.util.Iterator;
import java.util.LinkedList;

/**
*
* @author sandy
*/
import java.io.IOException;

/**
*
* @author sandy
*/
public class SpaceTime implements AnimSerializable {
    public Vector2D position;
    public int delta;
    
    public SpaceTime(int delt, Vector2D pos) {
        position = pos;
        delta = delt;
    }
    
    @Override
    public void serialize(AnimSerializer json) throws IOException {
        json.serialize(position);
        delta = json.serialize(delta);
    }
}