abstract class Figure {
    // Figure properties
    protected int lineColor;
    protected float lineSize;
    protected float locationX;
    protected float locationY;
    protected float size;
    protected float angle; // In degrees

    // Animations
    private TimedAnimation movement;
    private BlinkAnimation blinking;
    protected TimedAnimation growth;
    protected TimedAnimation rotation;

    Figure(int lineColor, float lineSize, float locationX, float locationY) {
        this.lineColor = lineColor;
        this.lineSize = lineSize;
        this.locationX = locationX;
        this.locationY = locationY;
        size = 1;
        angle = 0;
    }

    void newTranslation(TimedAnimation anim) {
        movement = anim;
    }

    void newBlink(BlinkAnimation anim) {
        blinking = anim;
    }

    void newGrowth(TimedAnimation anim) {
        growth = anim;
    }

    void newRotation(TimedAnimation anim) {
        rotation = anim;
    }

    // Call this every frame to move the figure
    boolean move() {
        if (movement == null) return false;
        boolean moving = movement.anim();
        if (moving) {
            locationX = movement.x;
            locationY = movement.y;
        }
        return moving;
    }

    // Call this every frame to do a blink animation
    void blink() {
        if (blinking == null) return;
        blinking.anim();
        lineSize = blinking.lineSize;
    }

    // Call this every frame to do a grow animation
    boolean grow() {
        if (growth == null) return false;
        boolean growing = growth.anim();
        if (growing) {
            size = growth.x;
        }
        return growing;
    }

    // Call this every frame to do a rotation animation
    boolean turn() {
        if (rotation == null) return false;
        boolean rotating = rotation.anim();
        if (rotating) {
            angle = rotation.x;
        }
        return rotating;
    }

    abstract void display();
}
