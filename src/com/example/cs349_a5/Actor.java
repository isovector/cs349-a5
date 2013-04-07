package com.example.cs349_a5;

import java.io.IOException;
import java.util.LinkedList;

import android.graphics.Rect;

/**
*
* @author sandy
*/
public class Actor implements AnimSerializable {
    public Path path;
    
    public LinkedList<Doodle> doodles = new LinkedList<Doodle>();
    public Vector2D position = null;
    public Vector2D origin = new Vector2D();
    public Vector2D size;
    public Rect boundingBox;
    public boolean committed = false;
    
    @Override
    public void serialize(AnimSerializer json) throws IOException {
        int size = json.serialize(0);
        for (int i = 0; i < size; ++i) {
            Doodle doodle = new Doodle();
            json.serialize(doodle);
            doodles.add(doodle);
        }
        
        path = new Path();
        size = json.serialize(0);
        for (int i = 0; i < size; ++i) {
            SpaceTime st = new SpaceTime(0, new Vector2D());
            json.serialize(st);
            path.add(st);
        }
        
        committed = true;
    }

    public void moveTo(int frame, Vector2D pos) {
        path.add(new SpaceTime(frame, pos));
    }
    
    public void paint(Graphics g, int frame) {
        Vector2D pos = (position != null) ? position : path.at(frame);
        g.translate((int)pos.x, (int)pos.y);
        
        for (Doodle doodle : doodles) {
            doodle.paint(g, frame);
        }
    }
}