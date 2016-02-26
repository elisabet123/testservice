package shortestwikipediapath.dataorganization;

import shortestwikipediapath.wikipediaclient.WikipediaClient;

import java.util.List;

/**
 * Created by elisabet on 2016-02-03.
 */
public class WikipediaGetter {
    WikipediaClient wikipediaClient = new WikipediaClient();

    public List<String> getLinks(String article) {
        return wikipediaClient.getLinks(article);
    }
}
