package cat.esteve.ships;

import java.util.Random;

public class Ship {
    private static Random r = new Random();
    private int x, y, lives;
    private boolean show = false;

    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
        this.lives = r.nextInt(3)+1;
    }

    public Ship(int x, int y, int lives) {
        this.x = x;
        this.y = y;
        this.lives = lives;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getLives() {
        return this.lives;
    }

    public void hit() {
        this.lives--;
        this.show = true;
    }

    public boolean show() {
        return this.show;
    }
}
