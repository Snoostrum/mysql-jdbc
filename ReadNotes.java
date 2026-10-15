import java.sql.*;
public class ReadNotes {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/learn?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String password = "mako114";

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("connected");

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, title, content FROM note");

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("content"));
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}