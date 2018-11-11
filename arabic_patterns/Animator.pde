class Animator {
    // Position
    protected float x;
    protected float y;
    float startX = 100;
    float startY = 100;
    float endX = 300;
    float endY = 30;

    // Stroke
    protected float lineAnimSize = 0;
    float maxLineSize = 10;

    // Animation parameters
    float easing = 0.12;
    float blinkingRate = 0.1;
    private float amplitudeX;
    private float amplitudeY;
    private float frame;
    private float speed = 0.04;
    private float duration;

    void newAnimation(float startX, float startY, float endX, float endY, float duration) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.x = startX;
        this.y = startY;
        amplitudeX = endX - startX;
        amplitudeY = endY - startY;
        this.duration = duration;
        speed = duration / frameRate;
        frame = 0;
    }

    /*
        A sigmoid animation (ease in - ease out) for x and y
    */
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

    void resize() {
        lineAnimSize = abs(sine(frameCount, blinkingRate, maxLineSize));
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

    private float sigmoid(float time, float frequency, float amplitude) {
        return (amplitude - cosine(time, frequency, amplitude)) + 1;
    }

}
