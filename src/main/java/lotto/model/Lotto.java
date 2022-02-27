package lotto.model;

import lotto.model.generator.LottoGenerator;
import lotto.model.number.BonusNumber;
import lotto.model.number.LottoNumbers;

public class Lotto {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_LAST_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    private final LottoNumbers lottoNumbers;
    private Rank rank;

    public Lotto(LottoGenerator autoLottoNumbersGenerator) {
        this.lottoNumbers = autoLottoNumbersGenerator.generateLottoNumbers(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER,
                LOTTO_LENGTH);
        this.rank = Rank.LOSER;
    }

    public Rank calculateRank(LottoNumbers winningNumbers, BonusNumber bonusNumber) {
        int count = countMatchingNumber(winningNumbers);
        boolean win = false;
        if (count == Rank.SECOND.getCount()) {
            win = lottoNumbers.containNumber(bonusNumber);
        }
        return this.rank = Rank.getRank(count, win);
    }

    private int countMatchingNumber(LottoNumbers winningNumbers) {
        return lottoNumbers.countMatchingNumber(winningNumbers);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public Rank getRank() {
        return rank;
    }
}
