PImage img;

int squareColor = #265074;
int circleColor = #B60E0E;
float lineSize = 5;

int step = 0;

Line bottom;
Line top;
Line right;
Line left;
Rectangle square;
Line radius;
Circle circle;

void setup() {
    size(700, 700); // 1299, 1294 for the final result
    smooth();
    // Image
    img = loadImage("vitruvian-man.jpg");
    img.resize(width, height);
    background(img);

    // Square
    float squareX = width / 2;
    float squareY = height / 2 + 59;
    float squareWidth = width - 127;
    float squareHeight = height - 135;
    square = new Rectangle(squareColor, lineSize, squareX, squareY, squareWidth, squareHeight, 0);
    square.newPainting(0, 100, 2);
    square.newBlink(8);

    // Lines
    bottom = new Line(squareColor, lineSize, squareX, squareY + squareHeight / 2, squareHeight / 2, -90);
    top = new Line(squareColor, lineSize, squareX, squareY - squareHeight / 2, squareHeight / 2, 90);
    right = new Line(squareColor, lineSize, squareX + squareWidth / 2, squareY, squareWidth / 2, 180);
    left = new Line(squareColor, lineSize, squareX - squareWidth / 2, squareY, squareWidth / 2, 0);
    bottom.newGrowth(0, 1, 2);
    top.newGrowth(0, 1, 2);
    right.newGrowth(0, 1, 2);
    left.newGrowth(0, 1, 2);

    // Circle
    float circleX = width / 2;
    float circleY = height / 2 - 2;
    float circleRadius = (width - 10) / 2;
    circle = new Circle(circleColor, lineSize, circleX, circleY, circleRadius, 0);
    circle.newPainting(0, 100, 2);

    // Radius
    radius = new Line(circleColor, lineSize, circleX, circleY, circleRadius, 0);
    radius.newGrowth(0, 1, 2);
    radius.newRotation(0, 360, 2);
    radius.newBlink(8);

    // EXAMPLES
    // dot.newTranslation(width / 2, height / 2, 100 + width / 2, 100 + height / 2, 1);
    // dot.newGrowth(0, 1, 1);
    // dot.newRotation(0, 180, 1);
    // dot.newPainting(0, 100, 1);
}

void draw() {
    background(img);

    // EXAMPLES
    // dot.move();
    // dot.grow();
    // dot.turn();
    // dot.display();
    // dot.paint();

    switch(step) {
    case 0:
        square.paint();
        if(frameCount / frameRate > 2) step++; // second 2
        break;
    case 1:
        square.display();
        bottom.grow();
        bottom.display();
        top.grow();
        top.display();
        right.grow();
        right.display();
        left.grow();
        left.display();
        if(frameCount / frameRate > 4) step++; // second 4
        break;
    case 2:
        square.display();
        bottom.display();
        top.display();
        right.display();
        left.display();
        if(frameCount / frameRate > 6) step++; // second 6
        break;
    case 3:
        radius.grow();
        radius.display();
        if(frameCount / frameRate > 8) step++; // second 8
        break;
    case 4:
        radius.turn();
        radius.display();
        circle.paint();
        if(frameCount / frameRate > 10) step++; // second 10
        break;
    case 5:
        radius.turn();
        radius.display();
        circle.display();
        if(frameCount / frameRate > 12) step++; // second 12
        break;
    case 6:
        radius.display();
        radius.blink();
        circle.display();
        if(frameCount / frameRate > 14) step++; // second 14
        break;
    case 7:        
        radius.display();
        circle.display();
        square.blink();
        square.display();
        if(frameCount / frameRate > 16) step++; // second 14
        break;
    }
}
