package lotto.domain;

import java.util.Objects;

public class LottoCount {

    private static final int MINIMUM_MANUAL_LOTTO_COUNT = 0;
    private static final int MINIMUM_LOTTO_COUNT = 1;
    private static final String INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE = ("로또 구입 갯수는 최소 %d개 이상");

    private final int manualLottoCount;
    private final int lottoCount;

    public LottoCount(int lottoCount) {
        this(lottoCount, MINIMUM_MANUAL_LOTTO_COUNT);
    }

    public LottoCount(int lottoCount, int manualLottoCount) {
        validateCount(lottoCount, MINIMUM_LOTTO_COUNT);
        validateCount(manualLottoCount, MINIMUM_MANUAL_LOTTO_COUNT);
        this.lottoCount = lottoCount;
        this.manualLottoCount = manualLottoCount;
    }

    private void validateCount(int count, int criteriaLottoCount) {
        if (count < criteriaLottoCount) {
            throw new IllegalArgumentException(
                String.format(INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE, criteriaLottoCount));
        }
    }

    public int getLottoCount() {
        return lottoCount;
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
