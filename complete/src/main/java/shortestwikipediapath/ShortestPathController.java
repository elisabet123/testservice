package shortestwikipediapath;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by elisabet on 2016-02-03.
 */
@RestController
public class ShortestPathController {

    @RequestMapping("/getshortestpath")
    public ShortestPath getShortestPath(@RequestParam(value="from", defaultValue="Hello") String from,
                                 @RequestParam(value="to", defaultValue="World") String to) {
        return new ShortestPath(from, to, new String[0]);
    }
}
