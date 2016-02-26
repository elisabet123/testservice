package shortestwikipediapath.wikipediaclient;

import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by elisabet on 2016-02-03.
 */
public class WikipediaClientTest {
    @Test
    public void shouldWork() {
        List<String> list = new WikipediaClient().getLinks("veddige");
        System.out.println(list.size());
    }

    @Test
    public void shouldNotWork() {
        new WikipediaClient().getLinks("veddig123123e");

    }

}