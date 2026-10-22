package src;
class TaskFactory {
    static Task create(
        String type,
        int id,
        String title,
        Severity severity,
        Estimate estimate
    ) {
        if (type.equalsIgnoreCase("bug")) {
            return new BugTask(id, title, severity);
        } else if (type.equalsIgnoreCase("feature")) {
            return new FeatureTask(id, title, estimate);
        }

        return null;
    }
}