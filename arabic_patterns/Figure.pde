abstract class Figure  extends Animator {
    int lineColor;
    float lineSize;
    float locationX;
    float locationY;

    Figure(int lineColor, float lineSize, float locationX, float locationY) {
        this.lineColor = lineColor;
        this.lineSize = lineSize;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    boolean move() {
        boolean moving = anim();
        if (moving) {
            locationX = x;
            locationY = y;
        }
        return moving;
    }

    abstract void display();
}
