package com.example.cs349_a5;

public class Line {
    Vector2D source;
    Vector2D dest;
    
    public Line(int col, Vector2D src, Vector2D dst) {
        source = src;
        dest = dst;
    }
    
    public Line(int col, int x0, int y0, int x1, int y1) {
        source = new Vector2D(x0, y0);
        dest = new Vector2D(x1, y1);
    }
    
    public void paint(android.graphics.Path g) {
    	g.lineTo(dest.x, dest.y);
    }
}