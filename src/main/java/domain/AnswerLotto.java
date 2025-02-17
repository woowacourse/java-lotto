package domain;

import domain.enums.LottoNumber;

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
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        if (!isValidNumber(bonusNumber)) {
            throw new IllegalArgumentException("1~45 범위 이내여야 합니다.");
        }
    }

    private boolean isValidNumber(int number) {
        return number >= LottoNumber.MIN_RANGE.getNumber() && number <= LottoNumber.MAX_RANGE.getNumber();
    }
}
