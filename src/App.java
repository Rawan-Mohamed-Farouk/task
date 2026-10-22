package src;
import java.util.Scanner;

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