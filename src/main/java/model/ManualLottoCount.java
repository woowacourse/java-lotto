package model;

import java.util.Objects;

public class ManualLottoCount {
    private final int count;

    public ManualLottoCount(int count, Budget budget) {
        if (count < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
        if (budget.getMaxCountForLottoIssue() < count) {
            throw new IllegalArgumentException("수동 로또를 사기엔 돈이 모자랍니다.");
        }
        this.count = count;
    }

    public static ManualLottoCount parse(String text, Budget budget) {
        return new ManualLottoCount(Integer.parseInt(text), budget);
    }

    public boolean isSameCount(int count) {
        return this.count == count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManualLottoCount that = (ManualLottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
