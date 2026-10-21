import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

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

    boolean isDone() {
        return done;
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
                "Estimate must be greater than zero"
            );
        }

        this.estimate = estimate;
    }

    @Override
    void print() {
        super.print();
        System.out.println(
            "Type: Feature | Estimate: " + estimate + " hours"
        );
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

interface TaskSortStrategy {
    List<Task> sort(List<Task> tasks);
}

class SortByTitle implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        List<Task> sortedTasks = new ArrayList<>(tasks);

        sortedTasks.sort(
            Comparator.comparing(
                task -> task.title.toLowerCase()
            )
        );

        return sortedTasks;
    }}

class SortByStatus implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        List<Task> sortedTasks = new ArrayList<>(tasks);

        sortedTasks.sort(
            Comparator.comparing(Task::isDone)
        );

        return sortedTasks;
    }}

class SortByAddedOrder implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return new ArrayList<>(tasks);
    }}
class TaskManager {
    private Map<Integer, Task> tasks = new LinkedHashMap<>();

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

    void list(TaskSortStrategy strategy) {
        List<Task> taskList = new ArrayList<>(tasks.values());

        List<Task> sortedTasks = strategy.sort(taskList);

        for (Task task : sortedTasks) {
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
                "\n1 Add  2 Find  3 List  4 Done  5 Exit"
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
                    System.out.print(
                        "Severity (low/medium/high): "
                    );
                    severity = scanner.nextLine();

                } else if (type.equalsIgnoreCase("feature")) {
                    System.out.print("Estimate in hours: ");
                    estimate = scanner.nextDouble();
                    scanner.nextLine();
                }

                try {
                    Task task = TaskFactory.create(
                        type, id, title, severity, estimate
                    );

                    if (task != null) {
                        System.out.print(
                            "Tags separated by commas: "
                        );

                        String[] tags =
                            scanner.nextLine().split(",");

                        for (String tag : tags) {
                            if (!tag.trim().isEmpty()) {
                                task.addTag(tag.trim());
                            }
                        }

                        manager.add(task);
                    } else {
                        System.out.println("Invalid task type");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice == 2) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Task task = manager.find(id);

                if (task == null) {
                    System.out.println("Not found");
                } else {
                    task.print();
                }

            } else if (choice == 3) {
                System.out.println(
                    "Sort by: 1 Title  2 Status  3 Added Order"
                );

                int sortChoice = scanner.nextInt();
                scanner.nextLine();

                TaskSortStrategy strategy = null;

                if (sortChoice == 1) {
                    strategy = new SortByTitle();
                } else if (sortChoice == 2) {
                    strategy = new SortByStatus();
                } else if (sortChoice == 3) {
                    strategy = new SortByAddedOrder();
                } else {
                    System.out.println("Invalid sorting choice");
                }

                if (strategy != null) {
                    manager.list(strategy);
                }

            } else if (choice == 4) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                manager.complete(id);

            } else if (choice == 5) {
                break;

            } else {
                System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }
}
