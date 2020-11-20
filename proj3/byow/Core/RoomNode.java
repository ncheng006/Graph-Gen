package byow.Core;

public class RoomNode {
    private int startX;
    private int startY;
    private int width;
    private int height;
    private int centerX;
    private int centerY;
    public RoomNode(int x, int y, int w, int h) {
        startX = x;
        startY = y;
        width = w;
        height = h;
        centerX = x + w/2;
        centerY = y + h/2;
    }
    public Double distanceRoom(RoomNode other) {
        int xDist = this.startX - other.startX;
        int yDist = this.startY - other.startY;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
    public boolean equals(RoomNode other) {
        return this.startX == other.startX && this.startY == other.startY && this.width == other.width && this.height == other.height;
    }
}
