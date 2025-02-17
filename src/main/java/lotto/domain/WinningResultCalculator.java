package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningResultCalculator {

    private static final int INITIAL_AWARD_COUNT = 0;
    private static final int MATCH_COUNT_INCREMENT = 1;

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningResultCalculator(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 중복되지 않는 보너스 번호를 입력해 주세요.");
        }
    }

    public WinningResult countLottoPrizes(final List<Lotto> lottos) {
        Map<LottoAward, Integer> winningResult = initializeWinningResult();
        for (Lotto lotto : lottos) {
            int matchingCount = winningLotto.countMatchingLottoNumber(lotto);
            boolean isBonusNumberMatched = lotto.contains(bonusNumber);
            LottoAward lottoAward = LottoAward.from(matchingCount, isBonusNumberMatched);
            winningResult.merge(lottoAward, MATCH_COUNT_INCREMENT, Integer::sum);
        }
        return new WinningResult(winningResult);
    }

    private Map<LottoAward, Integer> initializeWinningResult() {
        Map<LottoAward, Integer> winningResult = new EnumMap<>(LottoAward.class);
        for (LottoAward lottoAward : LottoAward.ACTUAL_LOTTO_AWARD) {
            winningResult.put(lottoAward, INITIAL_AWARD_COUNT);
        }
        return winningResult;
    }
}
