package src;
enum Severity {
    LOW,
    MEDIUM,
    HIGH
}

enum Estimate {
    SMALL(2),
    MEDIUM(8),
    LARGE(16);

    private int hours;

    Estimate(int hours) {
        this.hours = hours;
    }

    int getHours() {
        return hours;
    }
}