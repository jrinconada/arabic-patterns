class Proportion {

    ArrayList<Line> lines;
    final float gap = 7;
    float fullHeight;

    Proportion(int lineColor, float lineSize, float locationX, float locationY, float fullHeight, int parts) {
        lines = new ArrayList();
        float length = fullHeight / parts - gap;
        for (int i = 0; i < parts; i++) {
            println(locationY * i * (length + gap));
            lines.add(new Line(lineColor, lineSize, locationX, gap + locationY + (i * (length + gap)), length, 90));
        }
    }

    void display() {
        for (Line l : lines) {
            l.display();
        }
    }

}
