package domain.lotto;

import domain.budget.Budget;

public class LottoCount {
    private static final Budget LOTTO_COST = Budget.amounts(1000);
    private static final int ZERO = 0;

    private final int lottoCount;

    private LottoCount(int lottoCount) {
        validateLottoCount(lottoCount);
        this.lottoCount = lottoCount;
    }

    public static LottoCount of(int lottoCount) {
        return new LottoCount(lottoCount);
    }

    public static LottoCount of(Budget budget) {
        Budget lottoCount = budget.divide(LOTTO_COST);
        return new LottoCount(lottoCount.getIntValue());
    }

    private void validateLottoCount(int lottoCount) {
        if (lottoCount <= ZERO) {
            throw new IllegalArgumentException("최소 한 개의 로또를 구매해야 합니다.");
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
