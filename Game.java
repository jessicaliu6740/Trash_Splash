import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class Game{
    String page;
    int trashNum;
    Trash[] trash;
    Fish [] fish;
    Hook hook;
    int canvasWidth;
    int canvasHeight;
    Font title;
    int score, highScore;
    int frame;
    boolean big;
    int currTrashIdx;
    Trash currTrash;
    boolean currSortTrash;
    int red, blue, green;
    int trashCaught;
    Timer timer;
    int min, sec, highMin, highSec;
    boolean first;
    boolean onSort;

    public Game() {
        page = "home";
        trashNum = 20;
        trash = new Trash[trashNum];
        fish = new Fish[5];
        hook = new Hook();
        canvasWidth = 1000;
        canvasHeight = 700;
        trashCaught = 0;
        score = 0;
        highScore = Integer.MIN_VALUE;
        currTrashIdx = 0;
        currSortTrash = false;
        onSort = false;
        title = new Font("Serif", Font.PLAIN, 100);
        frame = 0;
        big = false;
        red = 56;
        green = 69;
        blue = 232;
        first = true;
        TimerHandler th = new TimerHandler();
        timer = new Timer(1000, th);
        min = sec = 0;
        highMin = highSec = Integer.MIN_VALUE;
        try {
            title = Font.createFont(Font.TRUETYPE_FONT, new File("ChikiBubbles.ttf")).deriveFont(Font.PLAIN, 150f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < trashNum; i++) {
            Trash temp = new Trash();
            trash[i] = temp;
        }
        for(int i = 0; i < 5; i++) {
            Fish temp = new Fish();
            fish[i] = temp;
        }
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, canvasWidth);
        StdDraw.setYscale(0, canvasHeight);
        StdDraw.enableDoubleBuffering();
    }

    public void initial()
    {
        page = "home-ocean";
        trashNum = 20;
        trash = new Trash[trashNum];
        fish = new Fish[5];
        hook = new Hook();
        canvasWidth = 1000;
        canvasHeight = 700;
        trashCaught = 0;
        score = 0;
        currTrashIdx = 0;
        currSortTrash = false;
        onSort = false;
        title = new Font("Serif", Font.PLAIN, 100);
        frame = 0;
        big = false;
        red = 56;
        green = 69;
        blue = 232;
        first = true;
        TimerHandler th = new TimerHandler();
        timer = new Timer(1000, th);
        min = sec = 0;
        try {
            title = Font.createFont(Font.TRUETYPE_FONT, new File("ChikiBubbles.ttf")).deriveFont(Font.PLAIN, 150f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < trashNum; i++) {
            Trash temp = new Trash();
            trash[i] = temp;
        }
        for(int i = 0; i < 5; i++) {
            Fish temp = new Fish();
            fish[i] = temp;
        }
    }

    public static void main(String [] args) {
        Game g = new Game();
        g.run();
    }

    public void run() {
        do {
            if (big) big = false;
            else {
                big = true;
            }
            if (page.equals("home")) {
                timer.stop();
                first = true;
                drawHome();
            } else if (page.equals("home-ocean") || page.equals("ocean-home")) {
                drawHomeOcean();
            } else if (page.equals("ocean")) {
                if(first) {
                    timer.start();
                    first = false;
                }
                drawOcean();
            } else if (page.equals("sort")){
                if(first) {
                    timer.start();
                    first = false;
                }
                drawSort();
            } else if (page.equals("gameover")) {
                timer.stop();
                first = true;
                drawGameOver();
            }
            else {
                drawHomeSort();
            }
        } while(true);
    }

    public void drawHome() {
        if (StdDraw.isMousePressed() && (StdDraw.mouseX() >= canvasWidth/2.0 - 75 && StdDraw.mouseX() <= canvasWidth/2.0 + 75
                && StdDraw.mouseY() >= 0 && StdDraw.mouseY() <= 33 + 25)) {
            if (!onSort) page = "home-ocean";
            else {
                page = "home-sort";
            } return;
        }
        StdDraw.setPenColor(95, 185, 227);
        StdDraw.filledRectangle(canvasWidth/2.0, canvasHeight/2.0, canvasWidth/2.0, canvasHeight/2.0);
        StdDraw.setPenColor(56, 69, 232);
        StdDraw.filledRectangle(canvasWidth/2.0, 75, canvasWidth/2.0, 150);
        StdDraw.picture(canvasWidth/2.0, 200, "fishingboat.png", 375, 400);
        StdDraw.setPenColor(255,255,255);
        if (big) {
            StdDraw.picture(canvasWidth/2.0, 23, "arrow.png", 45, 45);
            Font titleSmall = title.deriveFont(125f);
            StdDraw.setFont(titleSmall);
            StdDraw.text(canvasWidth/2.0, 590, "Trash");
            StdDraw.setFont(title);
            StdDraw.text(canvasWidth/2.0, 480, "Splash");
            StdDraw.picture(canvasWidth/2.0 + canvasWidth/2.9, 535, "splashRight.png", 290, 290);
            StdDraw.picture(canvasWidth/2.0 - canvasWidth/2.9, 535, "splashLeft.png", 290, 290);
        }
        else {
            StdDraw.picture(canvasWidth/2.0, 23, "arrow.png", 40, 40);
            Font titleSmall = title.deriveFont(135f);
            StdDraw.setFont(titleSmall);
            StdDraw.text(canvasWidth/2.0, 590, "Trash");
            Font titleTwo = title.deriveFont(140f);
            StdDraw.setFont(titleTwo);
            StdDraw.text(canvasWidth/2.0, 480, "Splash");
            StdDraw.picture(canvasWidth/2.0 + canvasWidth/2.9, 535, "splashRight.png", 300, 300);
            StdDraw.picture(canvasWidth/2.0 - canvasWidth/2.9, 535, "splashLeft.png", 300, 300);
        } StdDraw.pause(200);
        StdDraw.show();
    }

    public void drawHomeOcean() {
        if (page.equals("home-ocean")) {
            if (red >= 32) {
                StdDraw.setPenColor(red, green, blue);
                StdDraw.filledRectangle(canvasWidth / 2.0, canvasHeight / 2.0, canvasWidth / 2.0, canvasHeight / 2.0);
                red -= 2;
                green -= 2;
                blue -= 2;
            } else {
                page = "ocean";
            }
        } else {
            if (red <= 56) {
                StdDraw.setPenColor(red, green, blue);
                StdDraw.filledRectangle(canvasWidth / 2.0, canvasHeight / 2.0, canvasWidth / 2.0, canvasHeight / 2.0);
                red += 2;
                green += 2;
                blue += 2;
            } else {
                page = "home";
            }
        }
        drawHook(canvasWidth/2, hook.y - 250);
        StdDraw.show();
        StdDraw.pause(35);
    }

    public void drawOcean() {
        StdDraw.picture(canvasWidth/2.0, canvasHeight/2.0, "oceanBkg.png", canvasWidth + 200, canvasHeight);
        drawHook(hook.x, hook.y);
        StdDraw.picture(975, 675, "home.png", 40, 40);
        if (StdDraw.isMousePressed() && StdDraw.mouseX() >= 950 && StdDraw.mouseX() <= 1000
                && StdDraw.mouseY() >= 650 && StdDraw.mouseY() <= 700) {
            page = "ocean-home";
            return;
        }
        if (!hook.dropped && (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) || StdDraw.isMousePressed())) {
            hook.dropped = true;
        } if (hook.dropped) hook.drop();
        for (Trash t: trash) {
            drawTrash(t.x, t.y, t.degrees, t.name);
            if (checkOverlap(t.x, t.y)) {
                if(!t.caught)
                {
                    t.caught = true;
                    score += 100;
                    trashCaught++;
                }
                t.pickedUp = true;
                t.moveUp();
            }
        }

        for (Fish f: fish) {
            drawFish(f.x, f.y, f.name);
            if (checkOverlap(f.x, f.y)) {
                if(!f.caught)
                {
                    f.caught = true;
                    score -= 25;
                }
                f.pickedUp = true;
                f.moveUp();
            }
        }
        for (Trash t: trash) {
            if (!t.pickedUp) t.move();
        }
        for (Fish f: fish) {
            if (!f.pickedUp) f.move();
        }
        if (!hook.dropped) hook.move();

        Font scoreFont = title.deriveFont(50f);
        StdDraw.setFont(scoreFont);
        StdDraw.setFont(scoreFont);
        StdDraw.setPenColor(255, 255, 255);
        StdDraw.textLeft(10, 670, score + "");
        String timeString = "";
        if(sec < 10)
            timeString = min + ":0" + sec;
        else
            timeString = min + ":" + sec;
        StdDraw.textRight(945, 670, timeString);
        StdDraw.show();
        StdDraw.pause(30);
        if (trashCaught == trashNum && hook.y >= 900) {
            page = "home-sort";
            onSort = true;
        }
    }

    public void drawHomeSort() {
        StdDraw.setPenColor(0,0,0);
        StdDraw.filledRectangle(500, 350, 500,350);
        StdDraw.setPenColor(255, 255, 255);
        Font small = title.deriveFont(100f);
        StdDraw.setFont(small);
        StdDraw.text(500, 400, "Now Sort Your");
        StdDraw.setFont(title);
        StdDraw.text(500, 300,"TRASH");
        StdDraw.show();
        StdDraw.pause(2500);
        page = "sort";
    }

    public void drawTrash(int x, int y, int degrees, String name) {
        if(name.equals("bag.png")) {
            StdDraw.picture(x, y, name, 70, 80, degrees);
        }
        else if(name.equals("can.png")) {
            StdDraw.picture(x, y, name, 20, 35, degrees);
        }
        else if(name.equals("clearglass.png")) {
            StdDraw.picture(x, y, name, 90, 70, degrees);
        }
        else if(name.equals("greenglass.png")) {
            StdDraw.picture(x, y, name, 30, 65, degrees);
        }
        else if(name.equals("lid.png")) {
            StdDraw.picture(x, y, name, 50, 60, degrees);
        }
        else {
            StdDraw.picture(x, y, name, 25, 45, degrees);
        }
    }

    public void drawBigTrash(int x, int y, int degrees, String name) {
        int mult = 2;
        if(name.equals("bag.png")) {
            StdDraw.picture(x, y, name, 40*mult, 80*mult, degrees);
        }
        else if(name.equals("can.png")) {
            StdDraw.picture(x, y, name, 20*mult, 35*mult, degrees);
        }
        else if(name.equals("clearglass.png")) {
            StdDraw.picture(x, y, name, 90*mult, 70*mult, degrees);
        }
        else if(name.equals("greenglass.png")) {
            StdDraw.picture(x, y, name, 30*mult, 65*mult, degrees);
        }
        else if(name.equals("lid.png")) {
            StdDraw.picture(x, y, name, 50*mult, 60*mult, degrees);
        }
        else {
            StdDraw.picture(x, y, name, 25*mult, 45*mult, degrees);
        }
    }

    public void drawHook(int x, int y) {
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.setPenRadius(0.015);
        StdDraw.line(x - 4, y + 55, x, 1000);
        StdDraw.picture(x, y, "hook.png", 75, 110);
    }

    public void drawFish(int x, int y, String name) {
        StdDraw.picture(x, y, name, 35, 25);
    }

    public boolean checkOverlap(int x, int y) {
        if(hook.goingDown)  return false;
        if(hook.x >= x-50 && hook.x <= x+50 && hook.y - 50 >= y-55 && hook.y-50 <= y+55)
            return true;
        return false;
    }

    public void drawSort() {
        StdDraw.picture(canvasWidth/2, canvasHeight/2, "parkbkg.png", 1000, 700);
        if (!currSortTrash) {
            if (currTrashIdx >= trashNum) {
                page = "gameover";
                return;
            }
            currTrash = trash[currTrashIdx];
            currTrash.x = 500;
            currTrash.y = 725;
            currSortTrash = true;
        }
        if (currTrash.y <= -75) {
            if (currTrash.type.equals("plastic")) {
                if (currTrash.x >= 90 && currTrash.x <= 310) {
                    score += 100;
                } else {
                    score -= 25;
                }
            } else if (currTrash.type.equals("glass")) {
                if (currTrash.x >= 390 && currTrash.x <= 610) {
                    score += 100;
                } else {
                    score -= 25;
                }
            } else {
                if (currTrash.x >= 690 && currTrash.x <= 910) {
                    score += 100;
                } else {
                    score -= 25;
                }
            } currSortTrash = false;
            currTrashIdx++;
        }
        drawBigTrash(currTrash.x, currTrash.y, currTrash.degrees, currTrash.name);
        StdDraw.picture(200, 125, "plasticBin.png", 250, 250);
        StdDraw.picture(500, 125, "glassBin.png", 250, 250);
        StdDraw.picture(800, 125, "metalBin.png", 250, 250);
        StdDraw.picture(975, 675, "home.png", 40, 40);
        if (StdDraw.isMousePressed() && StdDraw.mouseX() >= 950 && StdDraw.mouseX() <= 1000
                && StdDraw.mouseY() >= 650 && StdDraw.mouseY() <= 700) {
            page = "home";
            return;
        }
        Font scoreFont = title.deriveFont(50f);
        StdDraw.setFont(scoreFont);
        StdDraw.setFont(scoreFont);
        StdDraw.setPenColor(255, 255, 255);
        StdDraw.textLeft(10, 670, score + "");
        String timeString = "";
        if(sec < 10)
            timeString = min + ":0" + sec;
        else
            timeString = min + ":" + sec;
        StdDraw.textRight(945, 670, timeString);
        StdDraw.show();
        StdDraw.pause(20);
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) || StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            currTrash.arrowMove(1);
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) || StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            currTrash.arrowMove(-1);
        } else {
            currTrash.arrowMove(0);
        }
    }

    public void drawGameOver() {
        if(StdDraw.isMousePressed()){
            page = "ocean";
            initial();
            return;
        }
        highScore = Math.max(score, highScore);
        if(min < highMin) {
            highMin = min;
            highSec = sec;
        }
        else if(min == highMin) {
            if(sec < highSec) {
                highMin = min;
                highSec = sec;
            }
        }
        StdDraw.setPenColor(95, 185, 227);
        StdDraw.filledRectangle(canvasWidth/2.0, canvasHeight/2.0, canvasWidth/2.0, canvasHeight/2.0);
        StdDraw.setPenColor(56, 69, 232);
        StdDraw.filledRectangle(canvasWidth/2.0, 75, canvasWidth/2.0, 150);
        StdDraw.picture(canvasWidth/2.0, 200, "fishingboat.png", 375, 400);
        StdDraw.setPenColor(255,255,255);
        Font scoreSmall = title.deriveFont(38f);
        Font scoreBig = title.deriveFont(40f);
        Font restartSmall = title.deriveFont(25f);
        Font restartBig = title.deriveFont(26f);
        String timeString = "";
        if(sec < 10)
            timeString = min + ":0" + sec;
        else
            timeString = min + ":" + sec;
        if (big) {
            Font titleSmall = title.deriveFont(125f);
            StdDraw.setFont(titleSmall);
            StdDraw.text(canvasWidth/2.0, 590, "Thank You");
            StdDraw.setFont(title);
            StdDraw.text(canvasWidth/2.0, 480, "For Playing");
            StdDraw.setFont(scoreSmall);
            StdDraw.text(200, 350, "Score: " + score);
            StdDraw.text(200, 300, "Time: " + timeString);
            StdDraw.setFont(restartSmall);
            StdDraw.text(825, 165, "Click Anywhere");
            StdDraw.setFont(scoreBig);
            StdDraw.text(750, 350, "Highscore: " + score);
            StdDraw.text(750, 300, "Best Time: " + timeString);
            StdDraw.setFont(restartBig);
            StdDraw.text(825, 125, "To Restart");
        }
        else {
            Font titleSmall = title.deriveFont(135f);
            StdDraw.setFont(titleSmall);
            StdDraw.text(canvasWidth/2.0, 590, "Thank You");
            Font titleTwo = title.deriveFont(140f);
            StdDraw.setFont(titleTwo);
            StdDraw.text(canvasWidth/2.0, 480, "For Playing");
            StdDraw.setFont(scoreBig);
            StdDraw.text(200, 350, "Score: " + score);
            StdDraw.text(200, 300, "Time: " + timeString);
            StdDraw.setFont(restartBig);
            StdDraw.text(825, 165, "Click Anywhere");
            StdDraw.setFont(scoreSmall);
            StdDraw.text(750, 350, "Highscore: " + score);
            StdDraw.text(750, 300, "Best Time: " + timeString);
            StdDraw.setFont(restartSmall);
            StdDraw.text(825, 125, "To Restart");
        } StdDraw.pause(200);
        StdDraw.show();
    }

    class TimerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            sec++;
            if(sec == 60) {
                min++;
                sec = 0;
            }
        }
    }
}