package lotto.domain;

import lotto.domain.exception.InvalidBounusBallException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BonusBall {
    private static final String BONUS_PATTERN = "^[0-9]*$";

    private LottoNumber bonus;

    public BonusBall(List<LottoNumber> winningLotto, String bonus) {
        this.bonus = invalidBonusBall(winningLotto, bonus);
    }

    private LottoNumber invalidBonusBall(List<LottoNumber> winningLotto, String bonus) {
        Matcher matcher = Pattern.compile(BONUS_PATTERN).matcher(bonus);
        LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(bonus));
        if (!matcher.find()) {
            throw new InvalidBounusBallException(bonus);
        }
        if (winningLotto.contains(bonusNumber)) {
            throw new InvalidBounusBallException(bonusNumber);
        }
        return bonusNumber;
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
