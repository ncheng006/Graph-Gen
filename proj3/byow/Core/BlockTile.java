package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class BlockTile {
    public int xPos;
    public int yPos;
    public TETile tile;

    public TETile[][] push(int dir, TETile[][] world) { // 0 = south, 1 = west, 2 = north, 3 = east
        dir = Math.floorMod(dir, 4);
        if (dir % 2 == 0) {
            while (world[xPos][yPos + dir - 1].equals(Tileset.FLOOR)) {
                int newYPos = yPos + dir - 1;
                world[xPos][yPos] = Tileset.FLOOR;
                world[xPos][newYPos] = tile;
                yPos = newYPos;
            }
        } else {
            while (world[xPos + dir - 2][yPos].equals(Tileset.FLOOR)) {
                int newXPos = xPos + dir - 2;
                world[xPos][yPos] = Tileset.FLOOR;
                world[newXPos][yPos] = tile;
                xPos = newXPos;
            }
        }
        return getAroundCorner(dir, world);
    }

    public TETile[][] nudge(int dir, TETile[][] world) {
        dir = Math.floorMod(dir, 4);
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
        return getAroundCorner(dir, world);
    }

    public TETile[][] getAroundCorner(int dir, TETile[][] world) {
        int oldDir = dir;
        dir++;
        dir = Math.floorMod(dir, 4);
        if (dir % 2 == 0 && world[xPos + oldDir - 2][yPos].equals(Tileset.WALL)
                && world[xPos][yPos + dir - 1].equals(Tileset.FLOOR)) {
            int newYPos = yPos + dir - 1;
            world[xPos][yPos] = Tileset.FLOOR;
            world[xPos][newYPos] = tile;
            yPos = newYPos;
            return world;
        }
        if (dir % 2 == 1 && world[xPos][yPos + oldDir - 1].equals(Tileset.WALL)
                && world[xPos + dir - 2][yPos].equals(Tileset.FLOOR)) {
            int newXPos = xPos + dir - 2;
            world[xPos][yPos] = Tileset.FLOOR;
            world[newXPos][yPos] = tile;
            xPos = newXPos;
            return world;
        }

        dir -= 2;
        dir = Math.floorMod(dir, 4);
        if (dir % 2 == 0 && world[xPos + oldDir - 2][yPos].equals(Tileset.WALL)
                && world[xPos][yPos + dir - 1].equals(Tileset.FLOOR)) {
            int newYPos = yPos + dir - 1;
            world[xPos][yPos] = Tileset.FLOOR;
            world[xPos][newYPos] = tile;
            yPos = newYPos;
            return world;
        }
        if (dir % 2 == 1 && world[xPos][yPos + oldDir - 1].equals(Tileset.WALL)
                && world[xPos + dir - 2][yPos].equals(Tileset.FLOOR)) {
            int newXPos = xPos + dir - 2;
            world[xPos][yPos] = Tileset.FLOOR;
            world[newXPos][yPos] = tile;
            xPos = newXPos;
            return world;
        }

        return world;
    }
}
