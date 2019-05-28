package lotto.domain;

import java.util.List;

public class WinnerLotto {
    private final List<LottoNumber> winnerLotto;

    public WinnerLotto(String input) {
        this.winnerLotto = InputParser.parse(input);
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = 0;
        for (LottoNumber lottoNumber : lotto) {
            if (winnerLotto.contains(lottoNumber)) {
                matchCount++;
            }
        }
        return Rank.valueOf(matchCount);
    }
}
