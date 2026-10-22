package src;
import java.util.List;

interface TaskSortStrategy {
    List<Task> sort(List<Task> tasks);
}