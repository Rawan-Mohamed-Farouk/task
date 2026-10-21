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
        System.out.println(
            id + " " + title + " " +
            (done ? "Done" : "Pending") + " " + tags);
    }}
class BugTask extends Task {
    private String severity;

    BugTask(int id, String title, String severity) {
        super(id, title);
        if (severity == null ||
            !severity.matches("(?i)low|medium|high")) {
            throw new IllegalArgumentException(
                "Severity must be low, medium, or high");
        }
        this.severity = severity.toLowerCase();
    }
    @Override
    void print() {
        super.print();
        System.out.println("Type: Bug | Severity: " + severity);
    }}
class FeatureTask extends Task {
    private double estimate;
    FeatureTask(int id, String title, double estimate) {
        super(id, title);
        if (estimate <= 0) {
            throw new IllegalArgumentException(
                "Estimate must be greater than zero");
            
        }
        this.estimate = estimate;
    }
    @Override
    void print() {
        super.print();
        System.out.println(
            "Type: Feature | Estimate: " + estimate + " hours");
        
        }}
class TaskFactory {
    static Task create(
        String type,
        int id,
        String title,
        String severity,
        double estimate
    ) {
       if (type.equalsIgnoreCase("bug")) {
            return new BugTask(id, title, severity);
        } else if (type.equalsIgnoreCase("feature")) {
            return new FeatureTask(id, title, estimate);
        }

        return null;
    }}

class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();

    void add(Task task) {
        if (tasks.containsKey(task.id)) {
            System.out.println("ID already exists");
            return;
        }

        tasks.put(task.id, task);
        System.out.println("Task added successfully");
    }

    Task find(int id) {
        return tasks.get(id);
    }

    void list() {
        for (Task task : tasks.values()) {
            task.print();
            System.out.println("----");
        }}
    void complete(int id) {
        Task task = find(id);

        if (task != null) {
            task.complete();
            System.out.println("Task completed");
        } else {
            System.out.println("Not found");
        }}}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println(
                "1 Add  2 Find  3 List  4 Done  5 Exit"
            );

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Type (bug/feature): ");
                String type = scanner.nextLine();

                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Title: ");
                String title = scanner.nextLine();

                String severity = "";
                double estimate = 0;

                if (type.equalsIgnoreCase("bug")) {
                    System.out.print("Severity (low/medium/high): ");
                    severity = scanner.nextLine();
                } else if (type.equalsIgnoreCase("feature")) {
                    System.out.print("Estimate in hours: ");
                    estimate = scanner.nextDouble();
                    scanner.nextLine();
                }

                Task task = TaskFactory.create(
                    type, id, title, severity, estimate
                );

                if (task != null) {
                    System.out.print("Tags separated by commas: ");
                    String[] tags = scanner.nextLine().split(",");

                    for (String tag : tags) {
                        if (!tag.trim().isEmpty()) {
                            task.addTag(tag.trim());
                        }
                    }

                    manager.add(task);
                } else {
                    System.out.println("Invalid task type");
                }

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