package src;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class TaskManager {
    private Map<Integer, Task> tasks = new LinkedHashMap<>();

    void add(Task task) {
        if (tasks.containsKey(task.getId())) {
            System.out.println("ID already exists");
            return;
        }

        tasks.put(task.getId(), task);
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
        }}
    void start(int id) {
        Task task = find(id);

        if (task == null) {
            System.out.println("Not found");
        } else if (task.getStatus() == Status.IN_PROGRESS) {
            System.out.println("Task is already in progress");
        } else if (task.getStatus() == Status.DONE) {
            System.out.println("Task is already done");
        } else {
            task.start();
            System.out.println("Task started");
        }}
    void complete(int id) {
        Task task = find(id);

        if (task == null) {
            System.out.println("Not found");
        } else if (task.getStatus() == Status.DONE) {
            System.out.println("Task is already done");
        } else if (task.getStatus() == Status.PENDING) {
            System.out.println("Start the task before completing it");
        } else {
            task.complete();
            System.out.println("Task completed");
        }}}