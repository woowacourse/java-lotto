package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinLotto {
    public static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";


    private final Lotto winLotto;
    private final BonusBall bonusBall;

    public WinLotto(String[] winLotto, String bonusBall) {
        this.winLotto = new Lotto(toLottoNoList(winLotto));
        this.bonusBall = new BonusBall(bonusBall);
    }

    public List<LottoNo> toLottoNoList(String[] winLotto) {
        try {
            return Arrays.stream(winLotto)
                    .map(Integer::parseInt)
                    .map(LottoNo::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
        }
    }

    public int findHitCount(Lotto lotto) {
        return lotto.findHitCount(winLotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return bonusBall.contains(lotto);
    }
}
