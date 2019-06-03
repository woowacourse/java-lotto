package lotto.domain.lotto;

import lotto.domain.InvalidBounusBall;

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
        if (!matcher.find()) {
            throw new InvalidBounusBall("옳바르지 않은 입력입니다.");
        }
        if (winningLotto.contains(new LottoNumber(Integer.parseInt(bonus)))) {
            throw new InvalidBounusBall("당첨번호와 중복된 정수가 있습니다.");
        }
        return new LottoNumber(Integer.parseInt(bonus));
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
