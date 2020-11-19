package byow.Core;

public class Edge {
    private roomNode from;
    private roomNode to;

    public Edge(roomNode r1, roomNode r2) {
        from = r1;
        to = r2;
    }

    public double weight() {
        return from.distanceRoom(to);
    }

    public int compareTo(Edge other) {
        if (this.weight() < other.weight()) {
            return -1;
        } else if (this.weight() > other.weight()) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean equals(Edge other) {
        return this.from.equals(other.from) && this.to.equals(other.to);

    }
}
