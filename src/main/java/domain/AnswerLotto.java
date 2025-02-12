package domain;

import java.util.ArrayList;
import java.util.List;

public class AnswerLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    public AnswerLotto(Lotto lotto, final int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }
}
