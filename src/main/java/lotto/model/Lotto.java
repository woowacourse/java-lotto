package lotto.model;

import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class Lotto {

    private final LottoNumbers lottoNumbers;
    private Rank rank;

    public Lotto(final LottoGenerator lottoGenerator) throws IllegalArgumentException {
        this.lottoNumbers = lottoGenerator.generateLottoNumbers(LottoNumber.LOTTO_NUMBER_MINIMUM_RANGE, LottoNumber.LOTTO_NUMBER_MAXIMUM_RANGE,
                LottoNumbers.LOTTO_LENGTH);
        this.rank = null;
    }

    public Rank calculateRank(final LottoNumbers winningNumbers, final LottoNumber bonusNumber) {
        if (this.rank == null) {
            int matchingCount = countMatchingNumber(winningNumbers);
            boolean win = isWinBonusNumber(matchingCount, bonusNumber);
            return this.rank = Rank.getRank(matchingCount, win);
        }
        return this.rank;
    }

    private boolean isWinBonusNumber(final int matchingCount, final LottoNumber bonusNumber) {
        if (matchingCount == Rank.SECOND.getCount()) {
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
