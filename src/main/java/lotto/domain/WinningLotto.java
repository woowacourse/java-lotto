package lotto.domain;

import java.util.List;

public class WinningLotto {
    public static final int LOTTO_POSSESSION_NUMBER = 6;
    public static final double SECOND_RANK_MATCH_COUNT = 5.5;

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank getLottoResult(Lotto lotto) {
        double count = getMatchCount(lotto);
        return LottoRank.getRank(count);
    }

    private double getMatchCount(Lotto lotto) {
        long count = getCount(lotto);

        if (isSecondRank(count, lotto)) {
            return SECOND_RANK_MATCH_COUNT;
        }

        return count;
    }

    private boolean isSecondRank(long count, Lotto lotto) {
        return count == LOTTO_POSSESSION_NUMBER && lotto.getNumbers().contains(bonusNumber);
    }

    private long getCount(Lotto lotto) {
        List<Integer> winningNumbers = winningLotto.getNumbers();
        List<Integer> generalLottoNumbers = lotto.getNumbers();
        long matchCount = generalLottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
        if (generalLottoNumbers.contains(bonusNumber)) {
            matchCount++;
        }
        return matchCount;
    }
}
