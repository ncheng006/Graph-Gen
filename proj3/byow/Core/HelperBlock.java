package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class HelperBlock extends BlockTile {
    public HelperBlock(int x, int y) {
        xPos = x;
        yPos = y;
        tile = Tileset.EXTRA_BLOCK;
    }

    public TETile[][] push(int dir, TETile[][] world) { // 0 = south, 1 = west, 2 = north, 3 = east
        if (dir % 2 == 0) {
            if (world[xPos][yPos + dir - 1].equals(Tileset.FLOOR)) {
                int newYPos = yPos + dir - 1;
                world[xPos][yPos] = Tileset.FLOOR;
                world[xPos][newYPos] = tile;
                yPos = newYPos;
            }
        } else {
            if (world[xPos + dir - 2][yPos].equals(Tileset.FLOOR)) {
                int newXPos = xPos + dir - 2;
                world[xPos][yPos] = Tileset.FLOOR;
                world[newXPos][yPos] = tile;
                xPos = newXPos;
            }
        }
        return world;
    }
}
