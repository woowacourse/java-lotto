package lotto.model;

import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class Lotto {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_LAST_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    private final LottoNumbers lottoNumbers;
    private Rank rank;

    public Lotto(LottoGenerator autoLottoNumbersGenerator) throws RuntimeException {
        this.lottoNumbers = autoLottoNumbersGenerator.generateLottoNumbers(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER,
                LOTTO_LENGTH);
        this.rank = null;
    }

    public Rank calculateRank(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        if (this.rank == null) {
            int count = countMatchingNumber(winningNumbers);
            boolean win = isWinBonusNumber(count, bonusNumber);
            return this.rank = Rank.getRank(count, win);
        }
        return this.rank;
    }

    private boolean isWinBonusNumber(int count, LottoNumber bonusNumber) {
        if (count == Rank.SECOND.getCount()) {
            return lottoNumbers.containNumber(bonusNumber);
        }
        return false;
    }

    private int countMatchingNumber(LottoNumbers winningNumbers) {
        return lottoNumbers.countMatchingNumber(winningNumbers);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
