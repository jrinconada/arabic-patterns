abstract class Animation {
    // Animation parameters
    protected float easing = 0.12;
    protected float amplitudeX;
    protected float amplitudeY;
    protected float frame;
    protected float speed;
    protected float duration;

    protected float sine(float time, float frequency, float amplitude) {
        return sin(time * frequency) * amplitude;
    }

    private float cosine(float time, float frequency, float amplitude) {
        return cos(time * frequency + PI) * amplitude;
    }

    private float arctan(float time, float frequency, float amplitude) {
        return atan(time * frequency) * amplitude;
    }

    protected float sigmoid(float time, float frequency, float amplitude) {
        return (cosine(time + PI, frequency / 2, 1) + 1) * amplitude / 2;
    }

    // Call every frame to animate, return false when animation is over (if it is timed)
    abstract boolean anim();
}
