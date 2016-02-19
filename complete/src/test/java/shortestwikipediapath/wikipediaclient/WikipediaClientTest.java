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
/*
    @Test
    public void tessst() {

        Pattern pattern = Pattern.compile("\\[\\[.*?(\\|[^\\]]*)?\\]\\]");
        String content = "'''Veddige''' is a [[Urban areas in Sweden|locality]] situated in [[Varberg Municipality|hejhej]], [[Halland County]], [[Sweden]] with 2,045 inhabitants in 2010.<ref name=scb /> It is located near the river Viskan, about 20&nbsp;km from the central place of [[Varberg]]. The scenery of Veddige is dominated by a huge church from the 19th century. The village also gave its name to the parish. The three oldest words for parish are Wighöger 1347, in Wyghöghä 1378, and Widhöya 1418. Veddige's sports team is the local [[ice-hockey]] team, the [[Veddige Vipers]], playing in Division 3 of the Swedish League.\n";
        System.out.println(content);

        Matcher m = pattern.matcher(content);
        while (m.find()) {
            StringBuffer sb = new StringBuffer(content.substring(m.start(), m.end()));
            // remove initial [[
            sb.delete(0, 2);
            // remove trailing ]]
            sb.delete(sb.length() - 2, sb.length());
            // remove any aliases
            int index = sb.lastIndexOf("|");
            if (index > 0) sb.delete(index, sb.length());

            System.out.println(sb);
        }

    }
    */

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