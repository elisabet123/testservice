package shortestwikipediapath.wikipediaclient;

/**
 * Created by elisabet on 2016-02-03.
 */
public class WikipediaQueryBuilder {
    private static final String BASE_URL = "https://en.wikipedia.org/w/api.php";

    private String action = "query";

    private String title = "";

    public WikipediaQueryBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public String build() {
        return BASE_URL + "?action="+action+"&titles="+title+"&prop=revisions&rvprop=content&format=json";
    }
}
