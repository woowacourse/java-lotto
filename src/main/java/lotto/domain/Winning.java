package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Winning {
    private final Lotto lotto;
    private final int bonusNum;

    private Winning(List<Integer> numbers, int bonusNum) {
        this.lotto = Lotto.of(numbers);
        checkBonusNum(bonusNum);
        checkContainNum(bonusNum);
        this.bonusNum = bonusNum;
    }

    private void checkContainNum(int bonusNum) {
        if (lotto.containNumber(bonusNum)) {
            throw new IllegalArgumentException("보너스 번호가 당첨 번호에 포함될 수 없습니다.");
        }
    }

    private void checkBonusNum(int bonusNum) {
        if (bonusNum < 0 || bonusNum > 45) {
            throw new IllegalArgumentException("보너스 번호가 범위를 벗어났습니다.");
        }
    }

    public static Winning of(List<Integer> numbers, int bonusNum) {
        return new Winning(numbers, bonusNum);
    }

    public Rank checkWinner(Lotto lotto) {
        return Rank.valueOf(this.lotto.sameNumberCount(lotto), isBonusNum(lotto));
    }

    public boolean isBonusNum(Lotto lotto) {
        return lotto.containNumber(bonusNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winning winning = (Winning) o;
        return Objects.equals(lotto, winning.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
