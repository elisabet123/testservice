package shortestwikipediapath.dijkstra;

import com.sun.org.apache.xpath.internal.SourceTree;
import shortestwikipediapath.NoSuchArticleException;
import shortestwikipediapath.dataorganization.WikipediaGetter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by elisabet on 2016-02-03.
 */
public class Dijkstra {
    private final WikipediaGetter wikipediaGetter;

    public Dijkstra(WikipediaGetter wikipediaGetter) {
        this.wikipediaGetter = wikipediaGetter;
    }

    public String[] getShortestPath(String from, String to) {
        LinkedList<Path> paths = new LinkedList<>();
        paths.add(new Path(null, from));
        Path path = calculatePath(paths, to);

        List<String> allEntries = new LinkedList<>();
        while (path != null) {
            allEntries.add(0,path.getName());
            path = path.getPrevious();
        }
        return allEntries.toArray(new String[0]);
    }

    private Path calculatePath(LinkedList<Path> paths, String goal) {
        Path previous = paths.pollFirst();
        if (previous == null) throw new NoSuchArticleException(goal);

        List<String> neighbours = wikipediaGetter.getLinks(previous.getName());

        for (String name : neighbours) {
            Path path = new Path(previous, name);
            if (goal.equals(name)) return path;
            paths.add(path);
        }

        return calculatePath(paths, goal);
    }

}
