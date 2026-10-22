package src;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class SortByStatus implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        List<Task> sortedTasks = new ArrayList<>(tasks);

        sortedTasks.sort(
            Comparator.comparing(Task::isDone)
        );

        return sortedTasks;
    }
}