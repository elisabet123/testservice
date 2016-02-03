package shortestwikipediapath.wikipediaclient;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by elisabet on 2016-02-03.
 */
public class WikipediaClientTest {

    @Test
    public void shouldWork() {
        new WikipediaClient().getLinks("veddige");

    }

    @Test
    public void shouldNotWork() {
        new WikipediaClient().getLinks("veddig123123e");

    }
}