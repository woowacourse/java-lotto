package lotto.domain;

public class LottoGenerator {

    private final int manualCount;
    private final int autoCount;

    public LottoGenerator(int manualCount, int autoCount) {
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    public static LottoGenerator of(Money money, int manualCount) {
        int totalCount = money.calculateLottoCount();
        if (totalCount < manualCount) {
            throw new IllegalArgumentException("[ERROR] 금액이 부족합니다.");
        }
        return new LottoGenerator(manualCount, totalCount - manualCount);
    }
}
