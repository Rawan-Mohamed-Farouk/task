package src;
import java.util.HashSet;
import java.util.Set;

class Task {
    int id;
    private String title;
    private Status status = Status.PENDING;
    Set<String> tags = new HashSet<>();

    Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    void addTag(String tag) {
        tags.add(tag);
    }

    void start() {
        status = Status.IN_PROGRESS;
    }

    void complete() {
        status = Status.DONE;
    }

     String getTitle() {
        return title;
    }

    Status getStatus() {
        return status;
    }

    void print() {
        System.out.println(
            id + " " + title + " " + status + " " + tags);
    }}