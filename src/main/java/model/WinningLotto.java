package model;

import global.utils.Validator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static global.constant.LottoConstant.MAX_LOTTO_NUMBER;
import static global.constant.LottoConstant.MIN_LOTTO_NUMBER;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(final String winningNumbers, final String bonusNumber) {
        winningLotto = new Lotto(winningNumbers);

        Validator.validateNumeric(bonusNumber);
        int parsed = Integer.parseInt(bonusNumber);

        Validator.validateRange(parsed, 1, 45);

        validateLottoNumberDuplicate(parsed);
        this.bonusNumber = parsed;
    }

    private Map<RankType, Integer> initializeRankResult() {
        Map<RankType, Integer> rankResult = new EnumMap<>(RankType.class);
        for (RankType rank : RankType.values()) {
            rankResult.put(rank, 0);
        }
        return rankResult;
    }

    private RankType evaluateLottoRank(final Lotto lotto) {
        int matchNumber = lotto.calculateMatchNumber(winningLotto);
        boolean isBonusNumber = isBonusNumber(matchNumber, lotto);
        return RankType.evaluateRank(matchNumber, isBonusNumber);
    }

    private boolean isBonusNumber(final int matchNumber, final Lotto lotto) {
        return matchNumber == 5 && lotto.isContained(this.bonusNumber);
    }
}
