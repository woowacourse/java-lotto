package domain;

public class AnswerLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    public AnswerLotto(Lotto lotto, final int bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validate(Lotto lotto, final int bonusNumber) {
        if (lotto.hasDuplicateNumber(bonusNumber)) {
            throw new IllegalArgumentException("");
        }
        if (!isValidNumber(bonusNumber)) {
            throw new IllegalArgumentException("");
        }
    }

    private boolean isValidNumber(int number) {
        return number > 0 && number <= 45;
    }
}
