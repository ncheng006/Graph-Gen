package byow.Core;

import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Engine {
    private TERenderer ter = new TERenderer();
    private TETile[][] finalWorldFrame;
    private AvatarHandler av;

    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;
    public static final int MAX_ROOM_WIDTH = 16;
    public static final int MAX_ROOM_HEIGHT = 10;

    public Boolean overLap(int x, int y, int width, int height) {
        int rightWall = x + width - 1;
        int topWall = y + height - 1;
        for (int i = x; i <= rightWall; i++) {
            for (int j = y; j <= topWall; j++) {
                if (finalWorldFrame[i][j].equals(Tileset.WALL)
                        || finalWorldFrame[i][j].equals(Tileset.FLOOR)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void createRoom(int x, int y, int width, int height) {
        if (overLap(x, y, width, height)) {
            return;
        }
        int rightWall = x + width - 1;
        int topWall = y + height - 1;
        for (int i = x; i <= rightWall; i++) {
            for (int j = y; j <= topWall; j++) {
                if (i == rightWall || j == topWall || i == x || j == y) {
                    finalWorldFrame[i][j] = Tileset.WALL;
                } else {
                    finalWorldFrame[i][j] = Tileset.FLOOR;
                }
            }
        }


    }
    /**
     * Method used for exploring a fresh world.
     * This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        String currSeed = "";
        boolean isThereS = false;
        while(!isThereS) {
            if(StdDraw.hasNextKeyTyped()) {
                String input = String.valueOf(StdDraw.nextKeyTyped());
                if(input.equals("S") || input.equals("s")) {
                    isThereS = true;
                }
                currSeed += input;
            }
        }
        interactWithInputString(currSeed);
        while(isThereS) {
            int xCoord = (int) StdDraw.mouseX();
            int yCoord = (int) StdDraw.mouseY() - 2;
            mouseInteract(xCoord,yCoord);
            if (StdDraw.hasNextKeyTyped()) {
                char next = StdDraw.nextKeyTyped();
                if (next == 'W' || next == 'w') {
                    finalWorldFrame = av.changePos(0, 1, finalWorldFrame);
                }
                if (next == 'A' || next == 'a') {
                    finalWorldFrame = av.changePos(-1, 0, finalWorldFrame);
                }
                if (next == 'S' || next == 's') {
                    finalWorldFrame = av.changePos(0, -1, finalWorldFrame);
                }
                if (next == 'D' || next == 'd') {
                    finalWorldFrame = av.changePos(1, 0, finalWorldFrame);
                }

            }
            mouseHighlight(xCoord,yCoord);
        }
    }

    public String[] loadingWorld(){
        File worldFile = new File("byow/Core/savedFile.txt");
        if(!worldFile.exists()) {
            return null;
        }
        String[] result = new String[3];
        try {
            FileReader reader = new FileReader(worldFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int index = 0;
            while ((line = bufferedReader.readLine()) != null) {
                result[index] = line;
                index++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void mouseInteract(int xCoord, int yCoord) {
        if(xCoord < WIDTH && xCoord >= 0 && yCoord < HEIGHT && yCoord >= 0) {
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(2, 1, finalWorldFrame[xCoord][yCoord].description() + " " + yCoord);
            StdDraw.show();
            mouseHighlight(xCoord,yCoord);
        }
    }

    public void mouseHighlight(int xCoord, int yCoord){
        if(xCoord < WIDTH && xCoord >= 0 && yCoord < HEIGHT && yCoord >= 0) {
            TETile sub = finalWorldFrame[xCoord][yCoord];
            finalWorldFrame[xCoord][yCoord] = new TETile(finalWorldFrame[xCoord][yCoord].character(), new Color(216, 128, 128),
                    new Color(255, 255, 0), finalWorldFrame[xCoord][yCoord].description());
            ter.renderFrame(finalWorldFrame);
            finalWorldFrame[xCoord][yCoord] = sub;
        }
    }
    /**
     * Method used for autograding and testing your
     * code. The input string will be a series
     * of characters (for example, "n123sswwdasda
     * ssadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these
     * characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should c
     * use the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"
     * ), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save.
     * If we then do
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
        StringInputDevice sid = new StringInputDevice(input);
        boolean seedReady = false;
        long seed = 0;
        while (sid.possibleNextInput()) {
            char current = sid.getNextKey();
            if (current == 'N' || current == 'n') {
                seedReady = true;
            }
            if (seedReady && Character.isDigit(current)) {
                seed *= 10;
                seed += Character.getNumericValue(current);
            }
            if (current == 'S' || current == 's') {
                break;
            }
        }
        ter.initialize(WIDTH,HEIGHT,0,2);
        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        createWorld(finalWorldFrame, seed);
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    public void createWorld(TETile[][] array, long seed) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                array[x][y] = Tileset.NOTHING;
            }
        }
        Random r = new Random(seed);
        int numRooms = Math.floorMod(RandomUtils.uniform(r,
                Integer.MAX_VALUE), 5) + 15;
        PriorityQueue<Edge> edges = new PriorityQueue();
        HashMap<RoomNode, Integer> fringe = new HashMap<>();
        int successfulRooms = 0;
        while (successfulRooms < numRooms) {
            int bottomLeftX = RandomUtils.uniform(r, 0,
                    WIDTH - MAX_ROOM_WIDTH);
            int bottomLeftY = RandomUtils.uniform(r, 0,
                    HEIGHT - MAX_ROOM_HEIGHT);
            int width = RandomUtils.uniform(r, 4, MAX_ROOM_WIDTH);
            int height = RandomUtils.uniform(r, 4,
                    MAX_ROOM_HEIGHT);

            if (!overLap(bottomLeftX, bottomLeftY, width, height)) {
                createRoom(bottomLeftX, bottomLeftY, width, height);
                RoomNode temp = new RoomNode(bottomLeftX,
                        bottomLeftY, width, height);
                for (RoomNode n : fringe.keySet()) {
                    edges.add(new Edge(n, temp));
                }
                fringe.put(temp, successfulRooms);
                successfulRooms++;
            }
            if(successfulRooms == numRooms) {
                av = new AvatarHandler(bottomLeftX + 1, bottomLeftY + 1, Tileset.AVATAR);
                array[bottomLeftX + 1][bottomLeftY + 1] = Tileset.AVATAR;
            }
        }
        WeightedQuickUnionUF wQUF = new WeightedQuickUnionUF(successfulRooms);
        kruskalSolver(edges, fringe, wQUF, successfulRooms);

    }

    public void kruskalSolver(PriorityQueue<Edge> edges, HashMap<RoomNode,
            Integer> fringe, WeightedQuickUnionUF wQUF, int succRooms) {
        int counter = 0;
        while (!edges.isEmpty() && counter < succRooms) {
            Edge temp = edges.poll();
            int fromInt = fringe.get(temp.getFrom());
            int toInt = fringe.get(temp.getTo());
            if (wQUF.connected(fromInt, toInt)) {
                continue;
            }
            wQUF.union(fromInt, toInt);
            helperTEConnect(temp);
            counter += 1;
        }
    }
    public void wallConnecter(int fromX, int fromY) {
        if (!finalWorldFrame[fromX + 1][fromY - 1].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX + 1][fromY - 1] = Tileset.WALL;
        }
        if (!finalWorldFrame[fromX - 1][fromY + 1].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX - 1][fromY + 1] = Tileset.WALL;
        }
        if (!finalWorldFrame[fromX - 1][fromY - 1].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX - 1][fromY - 1] = Tileset.WALL;
        }
        if (!finalWorldFrame[fromX + 1][fromY + 1].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX + 1][fromY + 1] = Tileset.WALL;
        }
        if (!finalWorldFrame[fromX][fromY + 1].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX][fromY + 1] = Tileset.WALL;
        }
        if (!finalWorldFrame[fromX][fromY - 1].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX][fromY - 1] = Tileset.WALL;
        }
        if (!finalWorldFrame[fromX + 1][fromY].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX + 1][fromY] = Tileset.WALL;
        }
        if (!finalWorldFrame[fromX - 1][fromY].equals(Tileset.FLOOR)) {
            finalWorldFrame[fromX - 1][fromY] = Tileset.WALL;
        }
    }
    public void helperTEConnect(Edge edge) {
        RoomNode from = edge.getFrom();
        RoomNode to = edge.getTo();
        int fromX = from.centerX;
        int fromY = from.centerY;
        int toX = to.centerX;
        int toY = to.centerY;
        while (fromX < toX) {
            if (!finalWorldFrame[fromX][fromY].equals(Tileset.FLOOR)) {
                finalWorldFrame[fromX][fromY] = Tileset.FLOOR;
            }
            wallConnecter(fromX, fromY);
            fromX++;
        }
        while (fromX > toX) {
            if (!finalWorldFrame[fromX][fromY].equals(Tileset.FLOOR)) {
                finalWorldFrame[fromX][fromY] = Tileset.FLOOR;
            }
            wallConnecter(fromX, fromY);
            fromX--;
        }
        while (fromY < toY) {
            if (!finalWorldFrame[fromX][fromY].equals(Tileset.FLOOR)) {
                finalWorldFrame[fromX][fromY] = Tileset.FLOOR;
            }
            wallConnecter(fromX, fromY);
            fromY++;
        }
        while (fromY > toY) {
            if (!finalWorldFrame[fromX][fromY].equals(Tileset.FLOOR)) {
                finalWorldFrame[fromX][fromY] = Tileset.FLOOR;

            }
            wallConnecter(fromX, fromY);
            fromY--;
        }
    }


    public static void main(String[] args) {
        Engine a = new Engine();
        a.interactWithKeyboard();
    }
}
