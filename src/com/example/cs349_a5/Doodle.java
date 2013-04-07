package com.example.cs349_a5;


import java.io.IOException;
import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Doodle implements AnimSerializable {
    public LinkedList<Line> lines = new LinkedList<Line>();
    public int firstFrame = 0;
    public int lastFrame = 99999999;
    Paint paint = new Paint();
    
    @Override
    public void serialize(AnimSerializer json) throws IOException {
        firstFrame = json.serialize(firstFrame);
        lastFrame = json.serialize(lastFrame);
        
        int size = json.serialize(0);
        Vector2D last = new Vector2D();
        Vector2D next = new Vector2D();
        json.serialize(last);
        
        for (int i = 0; i < size; ++i) {
            json.serialize(next);
            lines.add(new Line(0, (int)last.x, (int)last.y, (int)next.x, (int)next.y));
            last.x = next.x;
            last.y = next.y;
        }
        
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void paint(Canvas g, int frame) {
        if (frame < firstFrame || frame >= lastFrame) {
            return;
        }
        
        if (lines.isEmpty()) return;
        
        android.graphics.Path p = new android.graphics.Path();
        
        Vector2D first = lines.getFirst().source;
    	p.moveTo(first.x, first.y);
    	
        for (Line l : lines) {
            l.paint(p);
        }
        
        g.drawPath(p, paint);
    }
}