package src;
class BugTask extends Task {
    private String severity;

    BugTask(int id, String title, String severity) {
        super(id, title);

        if (severity == null ||
            !severity.matches("(?i)low|medium|high")) {
            throw new IllegalArgumentException(
                "Severity must be low, medium, or high");
        }
        this.severity = severity.toLowerCase();
    }

    @Override
    void print() {
        super.print();
        System.out.println("Type: Bug | Severity: " + severity);
    }
}