package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class GutendexAPI {
    private static final String BASE_URL = "https://gutendex.com/books";

    public List<GutendexResponse> searchBooks(String query) {
        return searchBooks(query, false);
    }

    public List<GutendexResponse> searchBooksByTitle(String title) {
        return searchBooks(title, true);
    }

    private List<GutendexResponse> searchBooks(String query, boolean searchOnlyTitles) {
        List<GutendexResponse> books = null;
        try {
            String encodedQuery = java.net.URLEncoder.encode(query, "UTF-8");
            URL url = new URL(BASE_URL + "?search=" + encodedQuery);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Reader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonElement resultsElement = jsonObject.get("results");

            Type listType = new TypeToken<List<GutendexResponse>>(){}.getType();
            books = gson.fromJson(resultsElement, listType);

            if (searchOnlyTitles) {
                String lowerCaseQuery = query.toLowerCase();
                books = books.stream()
                             .filter(book -> book.getTitle().toLowerCase().contains(lowerCaseQuery))
                             .collect(Collectors.toList());
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error al conectar con la API:");
            e.printStackTrace();
        }
        return books;
    }
}
