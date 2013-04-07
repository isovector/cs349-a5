package com.example.cs349_a5;

import java.io.IOException;
import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;

/**
*
* @author sandy
*/
public class Animation implements AnimSerializable {
    public LinkedList<Actor> actors = new LinkedList<Actor>();
    public LinkedList<Doodle> doodles = new LinkedList<Doodle>();

    public int currentFrame = 0;
	public boolean playing = false;
    
    @Override
    public void serialize(AnimSerializer json) throws IOException {
        int size = json.serialize(0);
        for (int i = 0; i < size; ++i) {
            Actor actor = new Actor();
            json.serialize(actor);
            actors.add(actor);
        }
        
        size = json.serialize(0);
        for (int i = 0; i < size; ++i) {
            Doodle doodle = new Doodle();
            json.serialize(doodle);
            doodles.add(doodle);
        }
    }
    
    void paint(Canvas g, int width, int height) {
    	g.drawColor(Color.WHITE);
        
        for (Doodle doodle : doodles) {
            doodle.paint(g, currentFrame);
        }
        
        Matrix identity = new Matrix();
        for (Actor actor : actors) {
            actor.paint(g, currentFrame);
            g.setMatrix(identity);
        }
    }
}