package domain;

import java.util.Objects;

public class LottoCount {

    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoCount(int manualLottoCount, int autoLottoCount) {
        validateLottoCountRange(manualLottoCount, autoLottoCount);
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = autoLottoCount;
    }

    private void validateLottoCountRange(int manualLottoCount, int autoLottoCount) {
        if (manualLottoCount < 0 || autoLottoCount < 0) {
            throw new IllegalArgumentException("로또 갯수는 0보다 작을 수 없습니다.");
        }
    }

    public int autoLottoCount() {
        return autoLottoCount;
    }

    public int manualLottoCount() {
        return manualLottoCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoCount that = (LottoCount)o;
        return manualLottoCount == that.manualLottoCount && autoLottoCount == that.autoLottoCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualLottoCount, autoLottoCount);
    }
}
