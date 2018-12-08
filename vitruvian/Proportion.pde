class Proportion {

    ArrayList<Line> lines;
    final float gap = 7;
    float fullHeight;

    private DiscreteAnimation painting;

    Proportion(int lineColor, float lineSize, float locationX, float locationY, float fullHeight, int parts) {
        lines = new ArrayList();
        float length = fullHeight / parts - gap;
        for (int i = 0; i < parts; i++) {
            lines.add(new Line(lineColor, lineSize, locationX, gap + locationY + (i * (length + gap)), length, 90));
        }
    }

    void newPainting(float from, float to, float duration) {
        painting = new DiscreteAnimation(from, to, duration);
    }

    void display() {
        for (Line l : lines) {
            l.display();
        }
    }

    protected boolean paint() {
        if (painting == null) return false;
        boolean stillPainting = painting.anim();
        if (stillPainting) {
            for (int i = 0; i < painting.x; i++) {
                lines.get(i).display();
            }
        }
        return stillPainting;
    }

}
