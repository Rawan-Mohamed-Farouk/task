package src;
class FeatureTask extends Task {
    private Estimate estimate;

    FeatureTask(int id, String title, Estimate estimate) {
        super(id, title);

        if (estimate == null) {
            throw new IllegalArgumentException(
                "Estimate must be small, medium, or large and bigger than 0");
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