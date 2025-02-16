package model;

import static global.constant.LottoConstant.MAX_LOTTO_NUMBER;
import static global.constant.LottoConstant.MIN_LOTTO_NUMBER;

import global.utils.Parser;
import model.utils.Validator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(final String winningNumbers, final String bonusNumber) {
        winningLotto = new Lotto(winningNumbers);

        int parsedBonusNumber = Parser.parseInteger(bonusNumber);
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

    private void validateBonusNumber(final int bonusNumber) {
        Validator.validateRange(bonusNumber, MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
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
