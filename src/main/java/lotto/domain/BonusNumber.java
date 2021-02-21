package lotto.domain;

public class BonusNumber {

    private final int bonus;

    public BonusNumber(int bonus) {
        validateBonus(bonus);
        this.bonus = bonus;
    }

    private void validateBonus(int bonus) {
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("1~45 사이의 숫자여야 합니다.");
        }
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BonusNumber)) return false;

        BonusNumber that = (BonusNumber) o;

        return bonus == that.bonus;
    }

    @Override
    public int hashCode() {
        return bonus;
    }
}
