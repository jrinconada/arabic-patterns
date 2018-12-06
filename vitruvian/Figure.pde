abstract class Figure {
    // Figure properties
    protected int lineColor;
    protected float lineSize;
    protected float locationX;
    protected float locationY;
    protected float size;
    protected float angle; // In degrees
    protected float howMuchPaint; // Percentage to finish (0 to 1)

    // Animations
    private TimedAnimation movement;
    private BlinkAnimation blinking;
    private TimedAnimation growth;
    private TimedAnimation rotation;
    private TimedAnimation painting;

    Figure (int lineColor, float lineSize, float locationX, float locationY) {
        this.lineColor = lineColor;
        this.lineSize = lineSize;
        this.locationX = locationX;
        this.locationY = locationY;
        size = 1;
        angle = 0;
    }

    void newTranslation(float xFrom, float yFrom, float xTo, float yTo, float duration) {
        movement = new TimedAnimation(xFrom, yFrom, xTo, yTo, duration);
    }

    void newBlink(float maxSize) {
        blinking = new BlinkAnimation(maxSize);
    }

    void newGrowth(float from, float to, float duration) {
        growth = new TimedAnimation(from, to, duration);
    }

    void newRotation(float from, float to, float duration) {
        rotation = new TimedAnimation(from, to, duration);
    }

    // from and to a are percentages (0 - 100)
    void newPainting(float from, float to, float duration) {
        painting = new TimedAnimation(from / 100, to / 100, duration);
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

    // Call this every frame to do a painting animation
    protected boolean draw() {
        if (painting == null) return false;
        boolean stillPainting = painting.anim();
        if (stillPainting) {
            howMuchPaint = painting.x;
        }
        return stillPainting;
    }

    abstract void display();
}
