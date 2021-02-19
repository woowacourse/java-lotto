package lotto.domain;

public class BonusNumber {

    private final int bonus;

    public BonusNumber(String bonus) {
        validateBonus(bonus);
        this.bonus = Integer.parseInt(bonus);
    }

    private void validateBonus(String bonus) {
        if (!bonus.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }
        if (Integer.parseInt(bonus) < 1 || Integer.parseInt(bonus) > 45) {
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
