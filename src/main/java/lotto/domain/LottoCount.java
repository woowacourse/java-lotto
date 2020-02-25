package lotto.domain;

import java.util.Objects;

public class LottoCount {

    private static final int MINIMUM_MANUAL_LOTTO_COUNT = 0;
    private static final int MINIMUM_LOTTO_COUNT = 1;
    private static final String INVALID_MINIMUM_LOTTO_COUNT_EXCEPTION_MESSAGE
        = "로또 구입 갯수는 최소 %d개 이상";
    private static final String INVALID_MAXIMUM_MANUAL_LOTTO_COUNT_EXCEPTION_MESSAGE
        = "수동 로또 갯수(%d)는 구매하려는 갯수(%d)보다 많을 수 없습니다.";

    private final int manualLottoCount;
    private final int lottoCount;

    public LottoCount(int lottoCount) {
        this(lottoCount, MINIMUM_MANUAL_LOTTO_COUNT);
    }

    public LottoCount(int lottoCount, int manualLottoCount) {
        validateMinimumCount(lottoCount, MINIMUM_LOTTO_COUNT);
        validateMinimumCount(manualLottoCount, MINIMUM_MANUAL_LOTTO_COUNT);
        validateMaximumManualCount(lottoCount, manualLottoCount);
        this.lottoCount = lottoCount;
        this.manualLottoCount = manualLottoCount;
    }

    private void validateMaximumManualCount(int lottoCount, int manualLottoCount) {
        if (lottoCount < manualLottoCount) {
            throw new IllegalArgumentException(String.format(
                INVALID_MAXIMUM_MANUAL_LOTTO_COUNT_EXCEPTION_MESSAGE, manualLottoCount,
                lottoCount));
        }
    }

    private void validateMinimumCount(int count, int criteriaLottoCount) {
        if (count < criteriaLottoCount) {
            throw new IllegalArgumentException(
                String.format(INVALID_MINIMUM_LOTTO_COUNT_EXCEPTION_MESSAGE, criteriaLottoCount));
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return lottoCount - manualLottoCount;
    }

    public boolean hasManualLottoCount() {
        return manualLottoCount != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoCount that = (LottoCount) o;
        return manualLottoCount == that.manualLottoCount &&
            lottoCount == that.lottoCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualLottoCount, lottoCount);
    }
}
