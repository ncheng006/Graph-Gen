package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static TETile[][] tiles;
    private static int height;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void addHexagon(int s,int begin,int height) {
        TETile a = randomTile();
        int start = begin;
        int end = start + s;
        int i = height;
        int rest = i + s;
        while(i < rest) {
            for( int j = start; j < end; j++) {
                tiles[j][i] = a;
            }
            start -=1;
            end +=1;
            i++;
        }
        rest = i + s;
        while(i < rest) {
            start +=1;
            end -=1;
            for( int j = start; j < end; j++) {
                tiles[j][i] = a;
            }
            i++;
        }

    }


    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            default: return Tileset.FLOWER;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        tiles = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
        for(int i = 0; i < 5*6; i = i + 6) {
            addHexagon(3,25,i);
        }
        for(int i = 3; i <= 4*6; i = i + 6) {
            addHexagon(3,20,i);
            addHexagon(3,30,i);
        }
        for(int i = 6; i <= 3*6; i = i + 6) {
            addHexagon(3,15,i);
            addHexagon(3,35,i);
        }
        ter.renderFrame(tiles);
    }


}
