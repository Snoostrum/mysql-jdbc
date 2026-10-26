import java.util.List;
import java.util.Scanner;

public class NoteApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println();
            System.out.println("1.列出   2.新增   3.删除   4.修改   0.退出");
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
            } else if (choice.equals("4")) {
                update();
            } else {
                System.out.println("没有这个选项");
            }
        }
        System.out.println("加纳");
    }

    private static void list() throws Exception {
        NoteDao noteDao = new NoteDao();
        List<Note> notes = noteDao.findAll();
        for (Note note : notes) {
            System.out.println(note.getId() + " | " + note.getTitle() + " | " + note.getContent());
        }
    }

    private static void add() throws Exception {
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

    private static void delete() throws Exception {
        System.out.print("要删的 id: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        int rows = new NoteDao().deleteById(id);
        if (rows == 0) {
            System.out.println("没有 id = " + id + " 这条");
        } else {
            System.out.println("OK啊也是直接删除了好吧");
        }
    }

    private static void update() throws Exception {
        System.out.print("要改的 id: ");
        int id = Integer.parseInt(sc.nextLine().trim());
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
            System.out.println("OK啊也是直接修改了好吧");
        }
    }
}