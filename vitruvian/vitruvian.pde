PImage img;

int lineColor = #265074;
float lineSize = 5;

int step = 0;

Line bottom;
Line top;
Line right;
Line left;
Rectangle square;

void setup() {
    size(700, 700); // 1300, 1300 for the final result
    smooth();
    // Image
    img = loadImage("vitruvian-man.jpg");
    img.resize(width, height);
    background(img);

    // Lines
    bottom = new Line(lineColor, lineSize, 64, height - 7, width - 125, -0.4);
    top = new Line(lineColor, lineSize, width - 67, 121, width - 131, -180.4);
    right = new Line(lineColor, lineSize, width - 61, height - 13, width - 135, 269.6);
    left = new Line(lineColor, lineSize, 65, 126, width - 131, 90);
    bottom.newGrowth(0, 1, 2);
    top.newGrowth(0, 1, 2);
    right.newGrowth(0, 1, 2);
    left.newGrowth(0, 1, 2);

    // Square
    square = new Rectangle(lineColor, lineSize, width / 2, height / 2 + 59, width - 128, height - 131, -0.6);

    // EXAMPLES
    // dot.newTranslation(width / 2, height / 2, 100 + width / 2, 100 + height / 2, 1);
    // dot.newGrowth(0, 1, 1);
    // dot.newRotation(0, 180, 1);
    square.newPainting(0, 100, 2);
}

void draw() {
    background(img);

    // EXAMPLES
    // dot.move();
    // dot.grow();
    // dot.turn();
    // dot.display();
    // dot.paintIt();

    switch(step) {
    case 0:
        // bottom.grow();
        // bottom.display();
        // top.grow();
        // top.display();
        // right.grow();
        // right.display();
        // left.grow();
        // left.display();
        square.paintIt();
        if(frameCount / frameRate > 2) step++; // 1 second
        break;
    case 1:
        // bottom.display();
        // top.display();
        // right.display();
        // left.display();
        square.display();
        break;
    }
}
