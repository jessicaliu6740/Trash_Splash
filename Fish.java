public class Fish {
    boolean pickedUp;
    boolean goingRight;
    boolean caught;
    int speed;
    int x, y;
    String name;

    public Fish() {
        pickedUp = false;
        caught = false;
        int direction = (int) (Math.random()*2);
        goingRight = direction == 0;
        speed = (int) (Math.random() * 5) + 1;
        x = (int) (Math.random() * 980);
        y = (int) (Math.random() * 550) + 25;
        if(goingRight)
            name = "rightFish.png";
        else
            name = "leftFish.png";
    }

    public void move() {
        if (goingRight) {
            if (x + speed <= 1075) {
                x += speed;
            } else {
                name = "leftFish.png";
                goingRight = false;
            }
        } else {
            if (x - speed >= -75) {
                x -= speed;
            } else {
                name = "rightFish.png";
                goingRight = true;
            }
        }
    }

    public void moveUp()
    {
        y += 40;
    }

}