package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class HelperBlock extends BlockTile {
    public HelperBlock(int x, int y) {
        xPos = x;
        yPos = y;
        tile = Tileset.EXTRA_BLOCK;
    }

    public TETile[][] push(int dir, TETile[][] world) {
        return super.nudge(dir, world);
    }
}
