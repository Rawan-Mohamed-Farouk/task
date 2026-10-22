package src;

import java.util.HashSet;
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

    boolean isDone() {
        return done;
    }

    void print() {
        System.out.println(
            id + " " + title + " " +
            (done ? "Done" : "Pending") + " " + tags);
    }
}