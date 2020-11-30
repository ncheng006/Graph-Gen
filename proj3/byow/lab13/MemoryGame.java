package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private Random bruh;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {

        long seed = Long.parseLong("1");
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        bruh = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StdDraw.clear();
        String a = "";
        for( int i = 0; i < n; i++) {
            int sub = bruh.nextInt(26);
            a += CHARACTERS[sub];
        }
        return a;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear();
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width/2,height/2,s);
        StdDraw.show();
    }

    public void flashSequence(String letters){
        //TODO: Display each character in letters, making sure to blank the screen between letters
        StdDraw.clear();
        String a = "";
        for(int i = 0; i < letters.length(); i++){
            a = String.valueOf(letters.charAt(i));
            System.out.println(a + " " + letters.length());
            StdDraw.text(width/2,height/2,a);
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.clear();
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String result = "";
        StdDraw.pause(5000);
        while(n > 0 && StdDraw.hasNextKeyTyped()) {
            result += StdDraw.nextKeyTyped();
            System.out.println(n);
            n--;
        }
        return result;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        int ran = 5;
        drawFrame("Round: ");
        String result = generateRandomString(ran);
        flashSequence(result);
        String answer = solicitNCharsInput(result.length());
        if(result.equals(answer)) {
            System.out.println("good");
        }
        //TODO: Establish Engine loop
    }

}
