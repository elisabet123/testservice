package shortestwikipediapath.dijkstra;

/**
 * Created by elisabet on 2016-02-03.
 */
public class Path {
    private final Path previous;
    private final int distance;
    private final String name;

    public Path(Path previous, String name) {
        this.previous = previous;
        this.name = name;
        this.distance = previous == null ? 0 : previous.getDistance() + 1;
    }

    public Path getPrevious() {
        return previous;
    }

    public int getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
}
