package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {

    private static final String DUPLICATED_NUMBER_ERROR = "[ERROR] 보너스볼 숫자는 당첨번호와 중복될 수 없습니다";
    private final Lotto winLotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        validateDuplicate(lotto, bonusBall);
        this.winLotto = lotto;
        this.bonusBall = bonusBall;
    }

    public void validateDuplicate(Lotto lotto, LottoNumber bonusBall) {
        if (lotto.isContain(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR);
        }
    }
    private int howManyWins(Lotto lotto) {
        List<LottoNumber> wins = new ArrayList<>(winLotto.getLottoNumbers());
        wins.retainAll(lotto.getLottoNumbers());
        return wins.size();
    }

    public Rank findRank(Lotto lotto) {
        int match = howManyWins(lotto);
        boolean bonusMatch = lotto.isContain(bonusBall);
        return Rank.makeRankByMatch(match, bonusMatch);
    }
}
