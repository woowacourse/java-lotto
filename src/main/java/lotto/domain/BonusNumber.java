package lotto.domain;

import java.util.function.Function;

public enum BonusNumber {
    TRUE(matchBonusNumber -> matchBonusNumber == true),
    FALSE(matchBonusNumber -> matchBonusNumber == false),
    DEFAULT(matchBonusNumber -> true);

    private Function<Boolean, Boolean> match;

    BonusNumber(Function<Boolean, Boolean> match) {
        this.match = match;
    }

    public boolean match(boolean matchBonusNumber) {
        return match.apply(matchBonusNumber);
    }
}
