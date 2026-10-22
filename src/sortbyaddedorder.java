package src;
import java.util.ArrayList;
import java.util.List;

class SortByAddedOrder implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return new ArrayList<>(tasks);
    }
}