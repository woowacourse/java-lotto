package domain.lotto;

import domain.budget.Budget;

public class LottoCount {
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
        return new LottoCount(budget.getIntValue());
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
