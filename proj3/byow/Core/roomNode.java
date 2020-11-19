package byow.Core;

public class roomNode {
    private int startX;
    private int startY;
    private int width;
    private int height;

    public roomNode(int x, int y, int w, int h) {
        startX = x;
        startY = y;
        width = w;
        height = h;
    }
    public Double distanceRoom(roomNode other) {
        int xDist = this.startX - other.startX;
        int yDist = this.startY - other.startY;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
    public boolean equals(roomNode other) {
        return this.startX == other.startX && this.startY == other.startY && this.width == other.width && this.height == other.height;
    }
}
