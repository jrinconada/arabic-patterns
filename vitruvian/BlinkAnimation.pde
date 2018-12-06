class BlinkAnimation extends Animation {
    // Stroke
    float lineSize;
    private float maxLineSize;
    private float blinkingRate = 0.1;

    BlinkAnimation (float maxLineSize) {
        this.maxLineSize = maxLineSize;
        this.duration = duration;
        lineSize = 0;
    }

    // Blinks indefinitely
    boolean anim() {
        lineSize = abs(sine(frameCount, blinkingRate, maxLineSize));
        return true;
    }
}
