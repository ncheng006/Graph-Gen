package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Test;

public class AvatarHandler {
    private TETile displayTile;
    public int xPos;
    public int yPos;

    public AvatarHandler(int x, int y, TETile tile) {
        xPos = x;
        yPos = y;
        displayTile = tile;
    }

    public TETile[][] changePos(int xInc, int yInc, TETile[][] world) {
        int newXPos = xPos + xInc;
        int newYPos = yPos + yInc;
        if (world[newXPos][newYPos].equals(Tileset.FLOOR)) {
            world[xPos][yPos] = Tileset.FLOOR;
            world[newXPos][newYPos] = displayTile;
            xPos = newXPos;
            yPos = newYPos;
        }
        return world;
    }

    public TETile getRelativeTile(int offsetX, int offsetY, TETile[][] world) {
        return world[xPos + offsetX][yPos + offsetY];
    }
}

