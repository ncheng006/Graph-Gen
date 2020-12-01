package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class AvatarHandler {
    private TETile displayTile;
    private int xPos;
    private int yPos;

    public AvatarHandler(int x, int y, TETile tile) {
        xPos = x;
        yPos = y;
        displayTile = tile;
    }

    public TETile[][] changePos(int xInc, int yInc, TETile[][] world) {
        int newXPos = xPos + xInc;
        int newYPos = yPos + yInc;
        if (!(world[newXPos][newYPos].equals(Tileset.WALL))) {
            world[xPos][yPos] = Tileset.FLOOR;
            world[newXPos][newYPos] = displayTile;
            xPos = newXPos;
            yPos = newYPos;
        }
        return world;
    }

}

