package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningResultCalculator {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningResultCalculator(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningResult makeWinningResult(final List<Lotto> lottos) {
        final Map<LottoAward, Integer> winningResult = initializeWinningResult();
        for (Lotto lotto : lottos) {
            final int matchingCount = winningLotto.countMatchingLottoNumber(lotto);
            final boolean isBonusNumberMatch = lotto.contains(bonusNumber);
            final LottoAward lottoAward = LottoAward.from(matchingCount, isBonusNumberMatch);
            winningResult.merge(lottoAward, 1, Integer::sum);
        }
        return new WinningResult(winningResult);
    }

    private void validate(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 중복되지 않는 보너스 번호를 입력해 주세요.");
        }
    }

    private Map<LottoAward, Integer> initializeWinningResult() {
        final Map<LottoAward, Integer> winningResult = new EnumMap<>(LottoAward.class);
        for (LottoAward lottoAward : LottoAward.ACTUAL_LOTTO_AWARD) {
            winningResult.put(lottoAward, 0);
        }
        return winningResult;
    }
}
