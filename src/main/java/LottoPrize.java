import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum LottoPrize {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchedNumberCount;
    private final boolean isBonusMatched;
    private final int price;

    LottoPrize(int matchedNumberCount, boolean isBonusMatched, int price) {
        this.matchedNumberCount = matchedNumberCount;
        this.isBonusMatched = isBonusMatched;
        this.price = price;
    }

    public static LottoPrize from(int matchedNumberCount, boolean isBonusMatched) {
        return Arrays.stream(values())
                .filter(value -> value.getMatchedNumberCount() == matchedNumberCount
                        && value.isBonusMatched() == isBonusMatched)
                .findFirst()
                .orElse(NONE);
    }

    public static List<LottoPrize> getSortedValues() {
        return Arrays.stream(values())
                .filter(v -> v != LottoPrize.NONE)
                .sorted(Comparator.comparing(LottoPrize::getPrice))
                .toList();
    }

    public String getInfo() {
        if (this == LottoPrize.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원)", matchedNumberCount, price);
        }
        return String.format("%d개 일치 (%d원)", matchedNumberCount, price);
    }

    public int getMatchedNumberCount() {
        return matchedNumberCount;
    }

    public boolean isBonusMatched() {
        return isBonusMatched;
    }

    public int getPrice() {
        return price;
    }
}
