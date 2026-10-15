import java.sql.*;
import java.util.Scanner;

public class NoteApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println();
            System.out.println("1) 列出   2) 新增   3) 删除   0) 退出");
            System.out.print("> ");

            String choice = sc.nextLine().trim();

            if (choice.equals("0")) {
                break;
            } else if (choice.equals("1")) {
                list();
            } else if (choice.equals("2")) {
                add();
            } else if (choice.equals("3")) {
                delete();
            } else {
                System.out.println("没有这个选项");
            }
        }
        System.out.println("再见");
    }

    private static void list() throws Exception {
        String sql = "SELECT id, title, content FROM note ORDER BY id";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("content"));
            }
        }
    }

    private static void add() throws Exception {
        System.out.print("标题: ");
        String title = sc.nextLine();
        System.out.print("内容: ");
        String content = sc.nextLine();

        String sql = "INSERT INTO note (title, content) VALUES (?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, content);
            ps.executeUpdate();
        }
        System.out.println("记下了");
    }

    private static void delete() throws Exception {
        System.out.print("要删的 id: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        String sql = "DELETE FROM note WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("没有 id = " + id + " 这条");
            } else {
                System.out.println("删掉了");
            }
        }
    }
}