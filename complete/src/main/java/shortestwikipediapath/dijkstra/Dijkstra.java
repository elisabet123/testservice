package shortestwikipediapath.dijkstra;

import shortestwikipediapath.NoSuchArticleException;
import shortestwikipediapath.dataorganization.WikipediaGetter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
        paths.add(new Path(null, from.toLowerCase()));

        Set<String> visited = new HashSet<>();
        visited.add(from);

        Path path = calculatePath(paths, visited, to.toLowerCase());

        List<String> allEntries = new LinkedList<>();
        while (path != null) {
            allEntries.add(0, path.getName());
            path = path.getPrevious();
        }
        return allEntries.toArray(new String[0]);
    }

    private Path calculatePath(LinkedList<Path> paths, Set<String> visited, String goal) {
        Path previous = paths.pollFirst();
        if (previous == null) throw new NoSuchArticleException(goal);

        System.out.println("---------------------------------------------- " + previous.getName() + " " + goal);
        List<String> neighbours = wikipediaGetter.getLinks(previous.getName());

        for (String name : neighbours) {
            name = name.toLowerCase();
            if (!visited.add(name)) continue;
            Path path = new Path(previous, name);
            if (goal.equals(name)) return path;
            paths.add(path);
        }

        return calculatePath(paths, visited, goal);
    }

}
