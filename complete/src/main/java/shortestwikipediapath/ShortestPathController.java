package shortestwikipediapath;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shortestwikipediapath.dataorganization.WikipediaGetter;
import shortestwikipediapath.dijkstra.Dijkstra;

/**
 * Created by elisabet on 2016-02-03.
 */
@RestController
public class ShortestPathController {
    private Dijkstra dijkstra = new Dijkstra(new WikipediaGetter());

    @RequestMapping("/getshortestpath")
    public ShortestPath getShortestPath(@RequestParam(value="from", defaultValue="Hello") String from,
                                 @RequestParam(value="to", defaultValue="World") String to) {
        String[] path = dijkstra.getShortestPath(from, to);
        return new ShortestPath(from, to, path);
    }
}
