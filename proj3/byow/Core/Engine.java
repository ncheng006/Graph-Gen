package byow.Core;

import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
<<<<<<< HEAD

import java.util.Random;
=======
>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8

public class Engine {
    TERenderer ter = new TERenderer();
    private TETile[][] finalWorldFrame;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;
    public static final int MAX_ROOM_WIDTH = 16;
    public static final int MAX_ROOM_HEIGHT = 10;


    public void createRoom(int x, int y, int width, int height) {
        int rightWall = x + width - 1;
        int topWall = y + height - 1;
        for(int i = x; i <= rightWall; i++) {
            for (int j = y; j <= topWall; j++) {
                if( i == rightWall || j == topWall || i == x || j == y) {
                    finalWorldFrame[i][j] = Tileset.WALL;
                } else {
                    finalWorldFrame[i][j] = Tileset.FLOOR;
                }
            }
        }

<<<<<<< HEAD
=======

    public void createRoom(int x, int y, int width, int length) {
        int rightWall = x + width - 1;
        int topWall = y + length - 1;
        for(int i = x; i <= rightWall; i++) {
            for (int j = y; j <= topWall; j++) {
                if( i == rightWall || j == topWall || i == x || j == y) {
                    finalWorldFrame[i][j] = Tileset.WALL;
                } else {
                    finalWorldFrame[i][j] = Tileset.FLOOR;
                }
            }
        }

>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8
    }
    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        StringInputDevice sid = new StringInputDevice(input);
        boolean seedReady = false;
        long seed = 0;

        while (sid.possibleNextInput()) {
            char current = sid.getNextKey();
<<<<<<< HEAD
            if (current == 'N' || current == 'n') {
=======
            if (current == 'N') {
>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8
                seedReady = true;
            }
            if (seedReady && Character.isDigit(current)) {
                seed *= 10;
                seed += Character.getNumericValue(current);
            }
<<<<<<< HEAD
            if (current == 'S' || current == 's') {
=======
            if (current == 'S') {
>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8
                break;
            }
        }

<<<<<<< HEAD
        ter.initialize(WIDTH, HEIGHT);
        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        createWorld(finalWorldFrame, seed);
=======
        ter.initialize(WIDTH,HEIGHT);
        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        createWorld(finalWorldFrame);
        createRoom(25,10,10,10);
>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

<<<<<<< HEAD
    public void createWorld(TETile[][] array, long seed) {
=======
    public void createWorld(TETile[][] array){
>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                array[x][y] = Tileset.NOTHING;
            }
        }
<<<<<<< HEAD
        Random r = new Random(seed);
        int numRooms = Math.floorMod(RandomUtils.uniform(r, Integer.MAX_VALUE), 5) + 8;
        for (int i = 0; i < numRooms; i++) {
            int bottomLeftX = RandomUtils.uniform(r, 0, WIDTH - MAX_ROOM_WIDTH);
            int bottomLeftY = RandomUtils.uniform(r, 0, HEIGHT - MAX_ROOM_HEIGHT);
            int width = RandomUtils.uniform(r, 4, MAX_ROOM_WIDTH);
            int height = RandomUtils.uniform(r, 4, MAX_ROOM_HEIGHT);
            System.out.println(bottomLeftX + " " + bottomLeftY + " " + width + " " + height);
            createRoom(bottomLeftX, bottomLeftY, width, height);
        }
=======
>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8
    }

    public static void main(String[] args) {
        Engine a = new Engine();
<<<<<<< HEAD
        a.interactWithInputString("n69420s");
=======
        a.interactWithInputString("N1234S");
>>>>>>> dc4bf75c0d572e6ef3e164b26139a7d71bd909d8
    }
}
