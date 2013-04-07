package com.example.cs349_a5;

/**
*
* @author sandy
*/
public class Animation implements AnimSerializable {
    public LinkedList<Actor> actors = new LinkedList<Actor>();
    public LinkedList<Doodle> doodles = new LinkedList<Doodle>();

    public int currentFrame = 0;
    
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
    
    void paint(Graphics g, int width, int height) {
        g.setColor(white);
        g.fillRect(0, 0, width, height);
        
        for (Doodle doodle : animation.doodles) {
            doodle.paint(g, animation.currentFrame);
        }
        
        for (Actor actor : animation.actors) {
            actor.paint(g, animation.currentFrame);
            ((Graphics2D)g).setTransform(identityTransform);
        }
    }
}