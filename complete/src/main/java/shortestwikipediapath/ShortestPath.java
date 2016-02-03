package shortestwikipediapath;

/**
 * Created by elisabet on 2016-02-03.
 */
public class ShortestPath {
    private final String start;
    private final String end;
    private final String[] path;

    public ShortestPath(String start, String end, String[] path) {
        this.start = start;
        this.end = end;
        this.path = path;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String[] getPath() {
        return path;
    }
}
