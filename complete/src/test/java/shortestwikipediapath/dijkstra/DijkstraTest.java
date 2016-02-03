package shortestwikipediapath.dijkstra;

import org.junit.Test;
import shortestwikipediapath.NoSuchArticleException;
import shortestwikipediapath.dataorganization.WikipediaGetter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by elisabet on 2016-02-03.
 */
public class DijkstraTest {
    Dijkstra dijkstra = new Dijkstra(new TestWiki());


    @Test
    public void calculateShortestPath() {
        String[] results = dijkstra.getShortestPath("hello", "goodbye");
        // two paths: hello -> world -> war -> death -> goodbye
        // two paths: hello -> life -> death -> goodbye
        assertArrayEquals(new Object[]{"hello","life","death","goodbye"}, results);
    }

    @Test
    public void noPathFound() {
        try {
            String[] results = dijkstra.getShortestPath("hello", "goodbye123");
        } catch (NoSuchArticleException e) {
            return;
        }
        assertNotNull(null);
    }@Test

    public void canHandleCapitals() {
        String[] results = dijkstra.getShortestPath("HELlo", "goodbyE");
        // HELlo should be normalized to hello, goodbyE to goodbye
        assertArrayEquals(new Object[]{"hello","life","death","goodbye"}, results);
    }

    /*
     * hello -> [world, friend, life]
     * world -> [war, peace]
     * friend -> [love, beer]
     * life -> [birth, death]
     * war -> [horrible, death]
     * peace -> [love, friend]
     * love -> []
     * beer -> [love]
     * birth -> [horrible]
     * death -> [goodbye]
     */
    private class TestWiki extends WikipediaGetter {
        @Override
        public List<String> getLinks(String article) {
            List<String> results = new ArrayList<>();
            if ("hello".equals(article)) {
                results.add("world");
                results.add("friend");
                results.add("life");
            } else if ("world".equals(article)) {
                results.add("war");
                results.add("peace");
            } else if ("friend".equals(article)) {
                results.add("love");
                results.add("beer");
            } else if ("life".equals(article)) {
                results.add("birth");
                results.add("death");
            } else if ("war".equals(article)) {
                results.add("horrible");
                results.add("death");
            } else if ("birth".equals(article)) {
                results.add("horrible");
            } else if ("peace".equals(article)) {
                results.add("love");
                results.add("friend");
            } else if ("beer".equals(article)) {
                results.add("love");
            } else if ("death".equals(article)) {
                results.add("goodbye");
            } else if ("love".equals(article)) {
            } else if ("horrible".equals(article)) {
            } else if ("goodbye".equals(article)) {
            } else {
                throw new RuntimeException(article + " not in list");
            }
            return results;
        }
    }

}