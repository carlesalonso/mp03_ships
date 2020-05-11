package cat.esteve.ships;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private int bw = 5, bh = 5;
    private Ship[][] board;


    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(". : Vaixells : .");
        this.board = this.genBoard(5, 5, 5);
        int turns = 0;
        int dead;
        do {
            System.out.print("Introdueix les coordenades x y separades per un espai: ");
            String[] coords = sc.nextLine().split(" ");
            selectPoint(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
            dead = this.printBoard();
            System.out.println();
            turns++;
        } while(dead < 5);

        System.out.println("Has guanyat amb " + turns + " torns.");
    }

    public void selectPoint(int x, int y) {
        if(x < 0 || x > this.bw || y < 0 || y > this.bh) {
            System.out.println("Les coordenades indicades estan fora del tauler.");
            return;
        }

        if(this.board[x][y] != null) this.board[x][y].hit();
    }

    public Ship[][] genBoard(int n, int w, int h) {
        Random r = new Random();
        Ship[][] res = new Ship[w][h];
        for(int i = 0; i < n; i++) {
            int xx = r.nextInt(5);
            int yy = r.nextInt(5);
            while (res[xx][yy] != null) {
                xx = r.nextInt(5);
                yy = r.nextInt(5);
            }
            res[xx][yy] = new Ship(xx, yy);
        }
        return res;
    }

    public int printBoard() {
        // Coordenades de la taula
        System.out.print("   ");
        for(int i = 0; i < this.bw; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 0; i < this.bw; i++) {
            System.out.print(" -" );
        }
        System.out.println();
        // Fi de les coordenades de la taula

        int hidden = 0;
        int dead = 0;
        for(int y = 0; y < this.bw; y++) {
            System.out.print(y + " |");
            for(int x = 0; x < this.bh; x++) {
                Ship s = this.board[x][y];
                if(s != null) {
                    if (!s.show()) hidden++;
                    if(s.show())
                        if(s.getLives() > 0) {
                            System.out.print(" " + s.getLives());
                        } else {
                            dead++;
                            System.out.print(" X");
                        }
                    else
                        System.out.print(" O");
                } else {
                    System.out.print(" O");
                }
            }
            System.out.println();
        }
        System.out.println("Vaixells sense tocar: " + hidden);
        return dead;
    }
}
