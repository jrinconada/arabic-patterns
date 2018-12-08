class TimedAnimation extends Animation {
    // Position
    float x;
    float y;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    TimedAnimation (float startX, float endX, float duration) {
        init(startX, 0, endX, 0, duration);
    }

    TimedAnimation (float startX, float startY, float endX, float endY, float duration) {
        init(startX, startY, endX, endY, duration);
    }

    void init(float startX, float startY, float endX, float endY, float duration) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.x = startX;
        this.y = startY;
        amplitudeX = endX - startX;
        amplitudeY = endY - startY;
        this.duration = duration;
        speed = 1 / (duration * frameRate);
        frame = 0;
    }

    // A sigmoid animation (ease in - ease out) for x and y
    boolean anim() {
        if (frame / frameRate < duration) {
            x = sigmoid(frame, speed, amplitudeX) + startX;
            y = sigmoid(frame, speed, amplitudeY) + startY;
            frame++;
            return true;
        } else {
            return false;
        }
    }
}
