package lotto.domain;

import lotto.validator.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinLotto {
    public static final String COMMA = ",";

    private final Lotto winLotto;
    private final BonusBall bonusBall;

    public WinLotto(String winLotto, String bonusBall) {
        this.winLotto = new Lotto(toLottoNoList(winLotto.split(COMMA)));
        this.bonusBall = new BonusBall(bonusBall);
    }

    public List<LottoNo> toLottoNoList(String[] winLotto) {
        try {
            return Arrays.stream(winLotto)
                    .map(Integer::parseInt)
                    .map(LottoNo::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputValidator.ERROR_MESSAGE_NOT_INTEGER);
        }
    }

    public int findHitCount(Lotto lotto) {
        return lotto.findHitCount(winLotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.contains(lotto);
    }
}
