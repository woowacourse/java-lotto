package lotto.domain;

import lotto.validator.Validator;

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
            throw new IllegalArgumentException(Validator.ERROR_MESSAGE_NOT_INTEGER);
        }
    }

    public int compare(Lotto lotto) {
        return lotto.compare(winLotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.isContainBonusBall(lotto);
    }
}
