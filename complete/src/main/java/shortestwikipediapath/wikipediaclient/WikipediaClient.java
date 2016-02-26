package shortestwikipediapath.wikipediaclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import shortestwikipediapath.NoSuchArticleException;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elisabet on 2016-02-03.
 */
public class WikipediaClient {
    public List<String> getLinks(String title) {
        String url = new WikipediaQueryBuilder().withTitle(title).build();

        try {
            String response = callWikipedia(url);
            return new ArrayList<>(parse(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String callWikipedia(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private Set<String> parse(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
        JsonNode pages = node.findValue("query").findValue("pages");


        Set<String> links = new HashSet<>();
        Pattern pattern = Pattern.compile("\\[\\[.*?\\]\\]");

        Iterator<Map.Entry<String, JsonNode>> it = pages.fields();

        while (it.hasNext()) {
            Map.Entry<String, JsonNode> entry = it.next();
            if ("-1".equals(entry.getKey())) {
                return links;
            }
            String content = entry.getValue().findValue("revisions").get(0).findValue("*").asText();

            Matcher m = pattern.matcher(content);
            while (m.find()) {
                StringBuffer sb = new StringBuffer(content.substring(m.start(), m.end()));
                // remove initial [[
                sb.delete(0,2);
                // remove trailing ]]
                sb.delete(sb.length() - 2, sb.length());
                // remove any aliases
                int indexPipe = sb.indexOf("|");
                if (indexPipe > 0) sb.delete(indexPipe, sb.length());
                // remove any subsections
                int indexHash = sb.indexOf("#");
                if (indexHash > 0) sb.delete(indexHash, sb.length());
                // remove any commas
                int indexComma = sb.indexOf(",");
                if (indexComma > 0) sb.delete(indexComma, sb.length());

                links.add(sb.toString());
            }
        }

        return links;
    }
}
