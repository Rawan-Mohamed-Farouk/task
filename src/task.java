package src;
import java.util.HashSet;
import java.util.Set;

class Task {
    private int id;
    private String title;
    private Status status = Status.PENDING;
    private Set<String> tags = new HashSet<>();

    Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    protected void addTag(String tag) {
        tags.add(tag);
    }

    void start() {
        status = Status.IN_PROGRESS;
    }

    void complete() {
        status = Status.DONE;
    }
    
    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    String getTitleLowerCase() {
        return title.toLowerCase();
    }

    Status getStatus() {
        return status;
    }

    void print() {
        System.out.println(
            id + " " + title + " " + status + " " + tags);
    }}