package domain;

import java.util.ArrayList;
import java.util.List;

public class AnswerLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    public AnswerLotto(Lotto lotto, final int bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
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
