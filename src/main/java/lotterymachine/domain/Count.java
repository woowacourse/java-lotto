package lotterymachine.domain;

public class Count {
    private final int autoValue;
    private final int passivityValue;

    public Count(int autoValue, int totalValue) {
        this.autoValue = autoValue;
        this.passivityValue = totalValue - autoValue;
    }

    public int getAutoValue() {
        return this.autoValue;
    }

    public int getPassivityValue() {
        return this.passivityValue;
    }
}
