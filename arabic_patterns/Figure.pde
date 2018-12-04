abstract class Figure {
    protected int lineColor;
    protected float lineSize;
    protected float locationX;
    protected float locationY;
    private TranslationAnimation movement;
    private BlinkAnimation blinking;

    Figure(int lineColor, float lineSize, float locationX, float locationY) {
        this.lineColor = lineColor;
        this.lineSize = lineSize;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    void newTranslation(TranslationAnimation anim) {
        movement = anim;
    }

    void newBlink(BlinkAnimation anim) {
        blinking = anim;
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

    abstract void display();
}
