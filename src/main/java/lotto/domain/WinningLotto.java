package lotto.domain;

import java.util.List;

import lotto.utils.InputParser;

public class WinningLotto {
    private static final int COUNT_INITIALIZE = 0;

    private final List<LottoNumber> winnerLotto;

    public WinningLotto(String input) {
        this.winnerLotto = InputParser.parseLotto(input);
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = COUNT_INITIALIZE;
        for (LottoNumber number : lotto) {
            matchCount = countAdder(matchCount, number);
        }
        return Rank.valueOf(matchCount);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return winnerLotto.contains(lottoNumber);
    }

    private int countAdder(int matchCount, LottoNumber lottoNumber) {
        if (winnerLotto.contains(lottoNumber)) {
            matchCount++;
        }
        return matchCount;
    }
}
