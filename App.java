import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Task {
    int id;
    String title;
    private boolean done = false;
    Set<String> tags = new HashSet<>();

    Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    void addTag(String tag) {
        tags.add(tag);
    }

    void complete() {
        done = true;
    }

    void print() {
        System.out.println(id + " " + title + " " + (done ? "Done" : "Pending") + " " + tags);
    }
}

class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();

    void add(Task task) {
        if (!tasks.containsKey(task.id)) {
            tasks.put(task.id, task);
        }
    }

    Task find(int id) {
        return tasks.get(id);
    }

    void list() {
        for (Task task : tasks.values()) {
            task.print();
        }
    }

    void complete(int id) {
        Task task = find(id);
        if (task != null) {
            task.complete();
        }
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("1 Add  2 Find  3 List  4 Done  5 Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Title: ");
                Task task = new Task(id, scanner.nextLine());
                System.out.print("Tags separated by commas: ");
                String[] tags = scanner.nextLine().split(",");

                for (String tag : tags) {
                    if (!tag.trim().isEmpty()) {
                        task.addTag(tag.trim());
                    }
                }
                manager.add(task);
            } else if (choice == 2) {
                Task task = manager.find(scanner.nextInt());
                scanner.nextLine();
                if (task == null) {
                    System.out.println("Not found");
                } else {
                    task.print();
                }
            } else if (choice == 3) {
                manager.list();
            } else if (choice == 4) {
                manager.complete(scanner.nextInt());
                scanner.nextLine();
            } else if (choice == 5) {
                break;
            }
        }
        scanner.close();
    }
}