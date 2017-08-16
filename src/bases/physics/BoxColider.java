package bases.physics;

import bases.GameObject;
import bases.Vector2D;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class BoxColider extends GameObject{
    private float width;
    private float height;

    public BoxColider(float x, float y, float width, float height){
        super();
        this.position.set(x, y);
        this.width = width;
        this.height = height;
    }

    public BoxColider(float width, float height){
        this(0,0, width, height);
    }

    public float left(){
        return this.screenPosition.x - this.width / 2;
    }

    public float right(){
        return this.screenPosition.x + this.width / 2;
    }

    public float top(){
        return this.screenPosition.y - this.height / 2;
    }

    public float bottom(){
        return this.screenPosition.y + this.height / 2;
    }

    public boolean intersects(BoxColider other){
        return this.bottom() >= other.top() &&
                this.top() <= other.bottom() &&
                this.right() >= other.left() &&
                this.left() <= other.right();
    }

    @Override
    public String toString() {
        return "BoxColider{" +
                "width=" + width +
                ", height=" + height +
                ", position=" + screenPosition +
                '}';
    }
}
