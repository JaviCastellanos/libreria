import com.google.gson.Gson;
import java.util.List;

public class ApiResponse {
    private List<Book> books;

    // Getters y Setters
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
