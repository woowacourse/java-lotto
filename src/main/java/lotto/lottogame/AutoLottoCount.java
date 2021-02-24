package lotto.lottogame;

import java.util.Objects;

public class AutoLottoCount {
    private int manualCount;

    public AutoLottoCount(int manualCount) {
        this.manualCount = manualCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoLottoCount that = (AutoLottoCount) o;
        return manualCount == that.manualCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualCount);
    }
}
