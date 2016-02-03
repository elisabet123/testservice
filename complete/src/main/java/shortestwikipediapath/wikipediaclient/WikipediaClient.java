package shortestwikipediapath.wikipediaclient;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import shortestwikipediapath.NoSuchArticleException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by elisabet on 2016-02-03.
 */
public class WikipediaClient {
    public List<String> getLinks(String title) {
        String url = new WikipediaQueryBuilder().withTitle(title).build();

        try {
            String response = callWikipedia(url);
            parse(response);
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

    private List<String> parse(String jsonString) throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonParser parser  = factory.createParser(jsonString);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
        JsonNode pages = node.findValue("query").findValue("pages");


        List<String> links = new ArrayList<>();

        Iterator<Map.Entry<String, JsonNode>> it = pages.fields();

        while (it.hasNext()) {
            Map.Entry<String, JsonNode> entry = it.next();
            if ("-1".equals(entry.getKey())) {
                return links;
            }
            String content = entry.getValue().findValue("revisions").get(0).findValue("*").asText();
            System.out.println(content);
        }



        return links;
    }
}
