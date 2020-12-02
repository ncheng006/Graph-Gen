package byow.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile RED = new TETile('@', new Color(197, 17, 17),
            Color.black, "you");
    public static final TETile ORANGE = new TETile('@', new Color(239, 125, 13),
            Color.black, "you");
    public static final TETile YELLOW = new TETile('@', new Color(245, 245, 87),
            Color.black, "you");
    public static final TETile LIME = new TETile('@', new Color(80, 239, 57),
            Color.black, "you");
    public static final TETile GREEN = new TETile('@', new Color(17, 127, 45),
            Color.black, "you");
    public static final TETile AQUA = new TETile('@', new Color(57, 254, 221),
            Color.black, "you");
    public static final TETile BLUE = new TETile('@', new Color(19, 46, 209),
            Color.black, "you");
    public static final TETile PURPLE = new TETile('@', new Color(107, 47, 187),
            Color.black, "you");
    public static final TETile PINK = new TETile('@', new Color(237, 84, 186),
            Color.black, "you");
    public static final TETile WHITE = new TETile('@', new Color(214, 224, 240),
            Color.black, "you");




    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall");
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");

    public static final TETile EXTRA_BLOCK = new TETile('▢', new Color(113, 73, 30), Color.black,
            "extra support block");
    public static final TETile ICE_BLOCK = new TETile('▢', new Color(131, 148, 191), Color.black,
            "your block");
    public static final TETile GOAL = new TETile(' ', new Color(255, 210, 20),
            new Color(255, 210, 20), "goal");
}


