public class Trash {
    //fields
    boolean pickedUp;
    boolean goingRight;
    boolean caught;
    int speed;
    int degrees;
    int x, y;
    String type, name;

    public Trash() {
        pickedUp = false;
        caught = false;
        int n = (int)(Math.random()*3);
        int m = (int)(Math.random()*2);
        if(n == 0)
        {
            type = "plastic";
            if(m==0)
                name = "waterbottle.png";
            else
                name = "bag.png";
        }
        else if(n==1)
        {
            type = "metal";
            if(m==0)
                name = "can.png";
            else
                name = "lid.png";
        }
        else
        {
            type = "glass";
            if(m==0)
                name = "clearglass.png";
            else
                name = "greenglass.png";
        }

        int direction = (int) (Math.random()*2);
        goingRight = direction == 0;
        speed = (int) (Math.random() * 5) + 1;
        x = (int) (Math.random() * 980);
        y = (int) (Math.random() * 550) + 25;
        degrees = (int) (Math.random() * 40);
        if (direction == 0) degrees *= -1;
    }

    public void move() {
        if (goingRight) {
            if (x + speed <= 1075) {
                x += speed;
            } else {
                goingRight = false;
            }
        } else {
            if (x - speed >= -75) {
                x -= speed;
            } else {
                goingRight = true;
            }
        }
    }

    public void moveUp()
    {
        y += 40;
    }

    public void arrowMove(int direction) {
        if (direction == -1 && x > 20) {
            x -= 10;
        } else if (direction == 1 && x < 980) {
            x += 10;
        } y -= 10;
    }
}