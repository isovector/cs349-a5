package com.example.cs349_a5;


import java.util.Iterator;
import java.util.LinkedList;

/**
*
* @author sandy
*/
public class Path extends LinkedList<SpaceTime> {
    @Override
    public boolean add(SpaceTime st) {
        int i;
        for(i = 0; i < size(); ++i){
            SpaceTime item = get(i);

            if (item.delta == st.delta) {
                item.position = st.position;
                return true;
            } else if (item.delta > st.delta) {
                break;
            }
        }
        
        add(i, st);
        return true;
    }
    
    public Vector2D at(int frame) {
        for (int i = 0; i < size(); ++i) {
            SpaceTime st = get(i);
            
            if (st.delta > frame) {
                if (i == 0) {
                    return st.position;
                }
                
                SpaceTime last = get(i - 1);
                return last.position.minus(st.position).scalarMult((frame - last.delta) / (st.delta - last.delta)).plus(last.position);
            }
        }
        
        return get(size() - 1).position;
    }
    
    public void eraseAfter(int frame) {
        Iterator<SpaceTime> it = iterator();
        while (it.hasNext()) {
            SpaceTime st = it.next();
            
            if (st.delta > frame) {
                it.remove();
            }
        }
    }
}