package domain;

import java.util.List;

public class WinningLotto {

    private static final int DEFAULT_COUNT = 0;
    private static final int PLUS_COUNT = 1;
    private static final int NO_COUNT = 0;

    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int calculateSameLotto(Lotto otherLotto) {
        int matchCount = DEFAULT_COUNT;
        for (LottoNumber lottoNumber : winningNumbers) {
            matchCount += countNumber(otherLotto, lottoNumber);
        }
        return matchCount;
    }

    private int countNumber(Lotto otherLotto, LottoNumber lottoNumber) {
        if (otherLotto.hasSameNumber(lottoNumber)) {
            return PLUS_COUNT;
        }
        return NO_COUNT;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
