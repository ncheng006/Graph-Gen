package byow.Core;

import byow.TileEngine.Tileset;

public class StartBlock extends BlockTile {
    public StartBlock(int x, int y) {
        xPos = x;
        yPos = y;
        tile = Tileset.ICE_BLOCK;
    }
}
