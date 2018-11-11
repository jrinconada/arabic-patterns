class Animator {
    // Position
    protected float x;
    protected float y;
    float startX = 100;
    float startY = 100;
    float endX = 300;
    float endY = 30;

    // Stroke
    private float lineSize = 0;
    float maxLineSize = 10;

    // Animation parameters
    float easing = 0.12;
    float blinkingRate = 0.1;
    private float amplitudeX;
    private float amplitudeY;
    float speed = 0.04;

    void newAnimation(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.x = startX;
        this.y = startY;
        amplitudeX = (endX - startX);
        amplitudeY = (endY - startY);
    }

    /*
        A sigmoid animation (ease in - ease out) for x and y
    */
    boolean anim() {
        if (abs(endX - x) > 0.3) {
            x = arctan(frameCount, speed, amplitudeX) + startX;
        } else {
            return false;
        }

        if (abs(endY - y) > 0.3) {
            y = arctan(frameCount, speed, amplitudeY) + startY;
        } else {
            return false;
        }
        return true;
    }

    void blink() {
        lineSize = abs(sine(frameCount, blinkingRate, maxLineSize));
    }

    private float sine(float time, float frequency, float amplitude) {
        return sin(time * frequency) * amplitude;
    }

    private float cosine(float time, float frequency, float amplitude) {
        return cos(time * frequency) * amplitude;
    }

    private float arctan(float time, float frequency, float amplitude) {
        return atan(time * frequency) * amplitude;
    }

}
