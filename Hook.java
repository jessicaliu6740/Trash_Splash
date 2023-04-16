public class Hook {
    //fields
    boolean goingRight;
    boolean dropped;
    boolean goingDown;
    int xSpeed, ySpeed;
    int x, y;

    public Hook() {
        goingRight = true;
        dropped = false;
        goingDown = true;
        xSpeed = 5;
        ySpeed = 40;
        x = 500;
        y = 625;
    }

    public void move() {
        if (goingRight) {
            if (x + xSpeed <= 950) {
                x += xSpeed;
            } else {
                goingRight = false;
            }
        } else {
            if (x - xSpeed >= 50) {
                x -= xSpeed;
            } else {
                goingRight = true;
            }
        }
    }

    public void drop() {
        if (dropped && goingDown) {
            if (y - ySpeed >= 50) {
                y -= ySpeed;
            } else {
                goingDown = false;
            }
        } else if (dropped) {
            if (y + ySpeed <= 1000) {
                y += ySpeed;
            } else {
                goingDown = true;
                dropped = false;
                y = 625;
            }
        }
    }
}