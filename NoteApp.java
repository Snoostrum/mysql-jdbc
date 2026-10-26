//与用户交互

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoteApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("1.列出   2.新增   3.删除   4.修改   5.查看   0.退出");
            System.out.print("> ");

            try {
                String choice = sc.nextLine().trim();

                if (choice.equals("0")) {
                    break;
                } else if (choice.equals("1")) {
                    list();
                } else if (choice.equals("2")) {
                    add();
                } else if (choice.equals("3")) {
                    delete();
                } else if (choice.equals("4")) {
                    update();
                } else if (choice.equals("5")) {
                    view();
                } else {
                    System.out.println("杂鱼你在输什么呢");
                }
            } catch (SQLException e) {
                System.err.println("数据库操作失败，请确认 MySQL 已启动后重试。详细信息: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("读取数据库配置失败，请检查 db.properties 文件后重试。详细信息: " + e.getMessage());
            }
        }
        System.out.println("加纳");
    }

    private static void list() throws SQLException, IOException {
        NoteDao noteDao = new NoteDao();
        List<Note> notes = noteDao.findAll();
        for (Note note : notes) {
            System.out.println(note.getId() + " | " + note.getTitle() + " | " + note.getContent());
        }
    }

    private static void add() throws SQLException, IOException {
        System.out.print("标题: ");
        String title = sc.nextLine();
        System.out.print("内容: ");
        String content = sc.nextLine();

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        new NoteDao().add(note);
        System.out.println("OK啊也是直接添加成功了好吧");
    }

    private static void delete() throws SQLException, IOException {
        int id = readId("要删的 id: ");
        if (id == -1) return;

        int rows = new NoteDao().deleteById(id);
        if (rows == 0) {
            System.out.println("没有 id = " + id + " 这条");
        } else {
            System.out.println("真是无趣啊我大抵很快就会忘了你吧");
        }
    }

    private static void update() throws SQLException, IOException {
        int id = readId("要改的 id: ");
        if (id == -1) return;

        System.out.print("新的标题: ");
        String title = sc.nextLine();
        System.out.print("新的内容: ");
        String content = sc.nextLine();

        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);

        int rows = new NoteDao().update(note);
        if (rows == 0) {
            System.out.println("没有 id = " + id + " 这条");
        } else {
            System.out.println("将大局逆转吧");
        }
    }

    private static void view() throws SQLException, IOException {
        int id = readId("想看哪个呢~: ");
        if (id == -1) return;

        Note note = new NoteDao().findById(id);
        if (note == null) {
            System.out.println("没有 id = " + id + " 这条");
        } else {
            System.out.println(note.getId() + " | " + note.getTitle() + " | " + note.getContent());
        }
    }

    private static int readId(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return -1;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("请输入有效的数字 id，或直接回车取消。");
            }
        }
    }
}