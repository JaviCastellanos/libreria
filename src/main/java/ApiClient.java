import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ApiClient {
    public List<Book> getBooks(String filePath) {
        List<Book> books = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Gson gson = new Gson();
            books = gson.fromJson(reader, List.class);
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
