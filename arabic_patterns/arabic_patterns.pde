
int backgroundColor = 50;
int lineColor = 200;
float lineSize = 2;

int step = 0;

Point dot;

void setup() {
    size(640, 480);
    smooth();
    background(backgroundColor);
    dot = new Point(lineColor, 5, width / 2, height / 2);
    dot.newTranslation(new TranslationAnimation(width / 2, height / 2, 500, 300, 1));
    dot.newBlink(new BlinkAnimation(10));
}

void draw() {
    background(backgroundColor);

    dot.move();
    dot.display();
    dot.blink();

    // switch(step) {
    // case 0:
    //     if(frameCount / frameRate >= 1) { // Wait 1 second
    //         step++;
    //     }
    //     break;
    // case 1:
    //     if(frameCount / frameRate < 4) { // Wait 4 seconds
    //         dot.blink();
    //         dot.display();
    //     } else {
    //         step++;
    //     }
    //     break;
    // }
}
