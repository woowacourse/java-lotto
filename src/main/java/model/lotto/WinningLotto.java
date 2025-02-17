package model.lotto;

import global.utils.Validator;
import model.RankType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static model.lotto.LottoConstant.MAX_LOTTO_NUMBER;
import static model.lotto.LottoConstant.MIN_LOTTO_NUMBER;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(final String winningNumbers, final String bonusNumber) {
        winningLotto = new Lotto(winningNumbers);

        int parsedBonusNumber = parseBonusNumber(bonusNumber);
        validateBonusNumber(parsedBonusNumber);
        this.bonusNumber = parsedBonusNumber;
    }

    public Map<RankType, Integer> evaluateRank(final List<Lotto> lottos) {
        Map<RankType, Integer> rankResult = initializeRankResult();

        for (Lotto lotto : lottos) {
            RankType rank = evaluateLottoRank(lotto);
            rankResult.put(rank, rankResult.get(rank) + 1);
        }
        return rankResult;
    }

    private static int parseBonusNumber(final String bonusNumber) {
        Validator.validateNumeric(bonusNumber);
        return Integer.parseInt(bonusNumber);
    }

    private void validateBonusNumber(final int bonusNumber) {
        Validator.validateNumberRange(bonusNumber, MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue());
        validateLottoNumberDuplicate(bonusNumber);
    }

    private void validateLottoNumberDuplicate(final int bonusNumber) {
        if (winningLotto.isContained(bonusNumber)) {
            throw new IllegalArgumentException();
        }
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
