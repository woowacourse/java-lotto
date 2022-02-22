package domain;

public class LottoFactory {

    public int calculateCount(final Money money) {
        return money.calculateCounts();
    }
}
