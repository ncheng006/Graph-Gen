package byow.Core;

public class Edge implements Comparable<Edge> {
    private RoomNode from;
    private RoomNode to;

    public Edge(RoomNode r1, RoomNode r2) {
        from = r1;
        to = r2;
    }

    public RoomNode getFrom() {
        return from;
    }
    public RoomNode getTo() {
        return to;
    }
    public double weight() {
        return from.distanceRoom(to);
    }

    @Override
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
