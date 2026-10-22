package src;
class BugTask extends Task {
    private Severity severity;

    BugTask(int id, String title, Severity severity) {
        super(id, title);

        if (severity == null){
            throw new IllegalArgumentException(
                "Severity must be low, medium, or high");
        }

        this.severity = severity;
    }
    @Override
    void print() {
        super.print();
        System.out.println("Type: Bug | Severity: " + severity);
    }
}