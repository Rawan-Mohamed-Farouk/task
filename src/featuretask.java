package src;
class FeatureTask extends Task {
    private double estimate;

    FeatureTask(int id, String title, double estimate) {
        super(id, title);

        if (estimate <= 0) {
            throw new IllegalArgumentException(
                "Estimate must be greater than zero");
        }

        this.estimate = estimate;
    }

    @Override
    void print() {
        super.print();
        System.out.println(
            "Type: Feature | Estimate: " + estimate + " hours"
        );
    }
}