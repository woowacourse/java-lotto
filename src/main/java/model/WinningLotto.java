package model;

import utils.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(String input, String bonusNumber) {
        lotto = new Lotto(input);

        Validator.validateNumeric(bonusNumber);
        int parsed = Integer.parseInt(bonusNumber);

        Validator.validateRange(parsed, 1, 45);

        validateLottoNumberDuplicate(parsed);
        this.bonusNumber = parsed;
    }

    public Map<RankType, Integer> evaluateRank(List<Lotto> lottos) {
        Map<RankType, Integer> rankResult = new HashMap<>();

        rankResult.put(RankType.FIFTH, 0);
        rankResult.put(RankType.FOURTH, 0);
        rankResult.put(RankType.THIRD, 0);
        rankResult.put(RankType.SECOND, 0);
        rankResult.put(RankType.FIRST, 0);

        for (Lotto lotto1 : lottos) {
            boolean isBonusNumber = false;
            int matchNumber = lotto1.calculateMatchNumber(lotto);

            if (matchNumber == 5) {
                isBonusNumber = lotto1.isContained(this.bonusNumber);
            }
            RankType rank = RankType.evaluateRank(matchNumber, isBonusNumber);

            if (rank != RankType.NONE) {
                rankResult.put(rank, rankResult.get(rank) + 1);
            }
        }

        return rankResult;
    }

    private void validateLottoNumberDuplicate(int parsed) {
        if (lotto.isContained(parsed)) {
            throw new IllegalArgumentException();
        }
    }
}
