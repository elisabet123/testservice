package shortestwikipediapath;

/**
 * Created by elisabet on 2016-02-03.
 */
public class NoSuchArticleException extends RuntimeException {
    public NoSuchArticleException(String article) {
        super("There is no article with the title "+article);
    }
}
