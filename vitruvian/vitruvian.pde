PImage img;
int step = 0;

// Colors
int regularColor = 60;
int squareColor = #265074;
int circleColor = #B60E0E;
int phiColor = #40B611;
// Sizes
float lineSize = 5;
int regularTextSize = 60;
int largeTextSize = 90;
// Square dimensions
float squareX;
float squareY;
float squareWidth;
float squareHeight;
// Circle dimensions
float circleX;
float circleY;
float circleRadius;

// Figures
Line bottom;
Line top;
Line right;
Line left;
Rectangle square;
Line radius;
Circle circle;

// Proportions
Proportion fingers4;
Proportion fingers16;
Proportion fingers24;
Proportion fingers96;
Proportion heads8;

void setup() {
    size(700, 700); // 1299, 1294 for the final result
    smooth();

    // Image
    img = loadImage("vitruvian-man.jpg");
    img.resize(width, height);
    background(img);

    // Text
    PFont font = createFont("Cambria Math", 32);
    // String[] fontList = PFont.list();
    // printArray(fontList);
    textFont(font);

    // Square
    squareX = width / 2;
    squareY = height / 2 + 59;
    squareWidth = width - 127;
    squareHeight = height - 135;
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
    circleX = width / 2;
    circleY = height / 2 - 2;
    circleRadius = (width - 10) / 2;
    circle = new Circle(circleColor, lineSize, circleX, circleY, circleRadius, 0);
    circle.newPainting(0, 100, 2);

    // Radius
    radius = new Line(circleColor, lineSize, circleX, circleY, circleRadius, 0);
    radius.newGrowth(0, 1, 2);
    radius.newRotation(0, 360, 2);
    radius.newBlink(8);

    // Proportions
    fingers4 = new Proportion(squareColor, lineSize - 1, width - 95, 210, squareHeight / 24, 4);
    fingers4.newPainting(0, fingers4.lines.size(), 2);
    heads8 = new Proportion(squareColor, lineSize, 40, squareY - squareHeight / 2, squareHeight, 8);
    heads8.newPainting(0, 8, 2);

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

    fingers4.paint();
    heads8.paint();

    switch(step) {
    case 0: // Square appears
        square.paint();
        if(frameCount / frameRate > 2) step++; // second 2
        break;
    case 1: // Lines to the center
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
    case 2: // Square and lines stay
        square.display();
        bottom.display();
        top.display();
        right.display();
        left.display();
        if(frameCount / frameRate > 6) step++; // second 6
        break;
    case 3: // Radius appears
        radius.grow();
        radius.display();
        if(frameCount / frameRate > 8) step++; // second 8
        break;
    case 4: // Circle appears
        radius.turn();
        radius.display();
        circle.paint();
        if(frameCount / frameRate > 10) step++; // second 10
        break;
    case 5: // Circle and radius stay
        radius.turn();
        radius.display();
        circle.display();
        if(frameCount / frameRate > 12) step++; // second 12
        break;
    case 6: // Radius blinks
        radius.display();
        radius.blink();
        circle.display();
        drawText("R", circleX + circleRadius / 2 - 20, circleY - 10, circleColor);
        if(frameCount / frameRate > 14) { // Square blinks (second 14)
            square.blink();
            square.display();
            drawText("L", squareX - 20, squareY - squareHeight / 2 - 10, squareColor);
        }
        // Phi formula:
        if(frameCount / frameRate > 16) drawText("R", width / 5, height * 3/5, circleColor); // R (second 16)
        if(frameCount / frameRate > 17) drawText("⎯", width / 5 - 4, height * 3/5 + regularTextSize / 2 + 6, regularColor, largeTextSize); // - (second 17)
        if(frameCount / frameRate > 18) drawText("L", width / 5, height * 3/5 + regularTextSize, squareColor); // L (second 18)
        if(frameCount / frameRate > 19) drawText("=", width / 5 + 48, height * 3/5 + 28, regularColor); // = (second 19)
        if(frameCount / frameRate > 20) drawText("\u03C6", width / 5 + 97, height * 3/5 + 20, phiColor); // Phi (second 20)
        break;
    }
}

void drawText(String t, float x, float y, int textColor, int s) {
    textSize(s);
    fill(textColor);
    text(t, x, y);
}

void drawText(String t, float x, float y, int textColor) {
    textSize(regularTextSize);
    fill(textColor);
    text(t, x, y);
}
