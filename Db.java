import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Db {
    public static Connection getConnection() throws Exception {
        Properties config = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("db.properties"))) {
            config.load(in);
        }
        return DriverManager.getConnection(
                config.getProperty("db.url"),
                config.getProperty("db.user"),
                config.getProperty("db.password"));
    }
}