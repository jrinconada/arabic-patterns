class DiscreteAnimation extends Animation {
    // Position
    int x;
    private float start;
    private float end;

    DiscreteAnimation (float start, float end, float duration) {
        this.start = start;
        this.end = end;
        this.x = round(start);
        amplitudeX = end - start;
        this.duration = duration;
        speed = 1 / (duration * frameRate);
        frame = 0;
    }

    // A sigmoid animation (ease in - ease out) for x and y
    boolean anim() {
        if (frame / frameRate < duration) {
            x = round(sigmoid(frame, speed, amplitudeX) + start);
            frame++;
            return true;
        } else {
            return false;
        }
    }
}
