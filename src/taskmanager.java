package src;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        }
    }

    void complete(int id) {
        Task task = find(id);

        if (task != null) {
            task.complete();
            System.out.println("Task completed");
        } else {
            System.out.println("Not found");
        }
    }
}