package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinLotto {
    private static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";
    private static final String ERROR_MESSAGE_BONUS_OVERLAP = "당첨번호에 보너스볼이 포함되어 있습니다.";

    private final Lotto winLotto;
    private final BonusBall bonusBall;

    public WinLotto(String[] winLotto, String bonusBall) {
        validate(winLotto, bonusBall);
        this.winLotto = new Lotto(toLottoNoList(winLotto));
        this.bonusBall = new BonusBall(bonusBall);
    }

    private static List<LottoNo> toLottoNoList(String[] winLotto) {
        try {
            return Arrays.stream(winLotto)
                    .map(Integer::parseInt)
                    .map(LottoNo::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
        }
    }

    private void isBonusOverlap(String bonusBall, String number) {
        if (number.equals(bonusBall)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BONUS_OVERLAP);
        }
    }

    private void validate(String[] winLotto, String bonusBall) {
        if (winLotto.length != Lotto.LOTTO_SIZE) {
            throw new IllegalArgumentException(Lotto.ERROR_MESSAGE_SIZE);
        }
        for (String winNumber : winLotto) {
            isBonusOverlap(bonusBall, winNumber);
        }
    }

    public int calculateHitCount(Lotto lotto) {
        return lotto.calculateHitCount(winLotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.contains(lotto);
    }
}
