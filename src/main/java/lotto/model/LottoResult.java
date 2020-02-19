package lotto.model;

public enum LottoResult {
    THREE(3,5000) {
        public double result(int count) { return count * price; }},
    FOUR(4,50000) {
        public double result(int count) { return count * price; }},
    FIVE(5,150000) {
        public double result(int count) { return count * price; }},
    FIVE_BONUS(5,30000000) {
        public double result(int count) { return count * price; }},
    SIX(6,2000000000) {
        public double result(int count) { return count * price; }};

    protected final int price;
    private final int correct;

    LottoResult(int correct, int price) {
        this.correct = correct;
        this.price = price;
    }

    public abstract double result(int count);

    public int getPrice() {
        return price;
    }

    public int getCorrect() {
        return correct;
    }
}
