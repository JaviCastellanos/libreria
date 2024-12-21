import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    public static void connect() {
        String url = "jdbc:sqlite:literatura.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS books (\n"
                        + " id integer PRIMARY KEY,\n"
                        + " title text NOT NULL,\n"
                        + " author text NOT NULL\n"
                        + ");";
                stmt.execute(sql);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
