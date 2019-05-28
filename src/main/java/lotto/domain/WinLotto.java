package lotto.domain;

import java.util.List;

import lotto.utils.InputParser;

public class WinLotto {
    private static final int COUNT_INITIALIZE = 0;

    private final List<LottoNumber> winnerLotto;

    public WinLotto(String input) {
        this.winnerLotto = InputParser.parse(input);
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = COUNT_INITIALIZE;
        for (LottoNumber lottoNumber : lotto) {
            matchCount = countAdder(matchCount, lottoNumber);
        }
        return Rank.valueOf(matchCount);
    }

    private int countAdder(int matchCount, LottoNumber lottoNumber) {
        if (winnerLotto.contains(lottoNumber)) {
            matchCount++;
        }
        return matchCount;
    }
}
