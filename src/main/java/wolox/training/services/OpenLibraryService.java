package wolox.training.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wolox.training.models.Book;

public class OpenLibraryService {
    private String urlService = "https://openlibrary.org/api";

    public OpenLibraryService() {}

    public String getExternalApi(String externalApi) throws IOException {
        URL urlApi = new URL(externalApi);
        HttpURLConnection connection = (HttpURLConnection) urlApi.openConnection();
        connection.setRequestMethod("GET");
        StringBuffer response = new StringBuffer();
        String input;

        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((input = buffer.readLine()) != null) {
            response.append(input);
        }
        buffer.close();
        return response.toString();
    }

    private Book bookParserJson(String rawJsonResponse, String isbn) throws JSONException {
        JSONObject jsonRaw = new JSONObject(rawJsonResponse);
        if (jsonRaw.has("ISBN:" + isbn)) {
            JSONObject bookData = jsonRaw.getJSONObject("ISBN:" + isbn);
            Book bookParsed = new Book();
            String allAuthors = "";
            String allPublishers = "";
            bookParsed.setIsbn(isbn);
            bookParsed.setTitle(bookData.getString("title"));
            bookParsed.setSubtitle(bookData.getString("subtitle"));
            JSONArray publishers = bookData.getJSONArray("publishers");
            for (int i = 0; i < publishers.length(); i++) {
                allPublishers += ((JSONObject)publishers.get(i)).getString("name") + ".";
            }
            bookParsed.setPublisher(allPublishers);
            bookParsed.setYear(bookData.getString("publish_date"));
            bookParsed.setPages(bookData.getInt("number_of_pages"));
            JSONArray authors = bookData.getJSONArray("authors");
            for (int i = 0; i < authors.length(); i++) {
                allAuthors += ((JSONObject)authors.get(i)).getString("name") + ".";
            }
            bookParsed.setAuthor(allAuthors);
            JSONObject images = bookData.getJSONObject("cover");
            bookParsed.setImage(images.getString("small"));
            return bookParsed;
        } else {
            return null;
        }
    }

    public Book bookInfo(String isbn) {
        try {
            String response = getExternalApi(urlService + "/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data");
            Book bookParsed = bookParserJson(response, isbn);
            return bookParsed;
        } catch(Exception e) {
            return null;
        }
    }
}
