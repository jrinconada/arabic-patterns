class Animator {
    // Position
    float x = 500;
    float y = 300;
    float targetX = 200;
    float targetY = 200;
    float dx;
    float dy;

    // Stroke
    float lineSize = 0;
    float maxLineSize = 10;

    // Animation parameters
    float easing = 0.12;
    float blinkingRate = 0.1;

    void move() {
        dx = targetX - x;
        x += dx * easing;
        dy = targetY - y;
        y += dy * easing;
    }

    void blink() {
        lineSize = abs(sine(frameCount, blinkingRate, maxLineSize));
    }

    float sine(float time, float frequency, float amplitude) {
        return sin(time * frequency) * amplitude;
    }

    float cosine(float time, float frequency, float amplitude) {
        return cos(time * frequency) * amplitude;
    }

}
