//与数据库交互

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    public List<Note> findAll() throws SQLException, IOException {
        String sql = "SELECT id, title, content FROM note ORDER BY id";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Note> list = new ArrayList<>();
            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));
                list.add(note);
            }
            return list;
        }
    }

    public Note findById(int id) throws SQLException, IOException {
        String sql = "SELECT id, title, content FROM note WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));
                return note;
            }
        }
    }

    public void add(Note note) throws SQLException, IOException {
        String sql = "INSERT INTO note (title, content) VALUES (?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException, IOException {
        String sql = "DELETE FROM note WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int update(Note note) throws SQLException, IOException {
        String sql = "UPDATE note SET title = ?, content = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setInt(3, note.getId());
            return ps.executeUpdate();
        }
    }
}