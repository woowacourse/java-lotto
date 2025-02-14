package repository;

import domain.Lotto;
import domain.Rank;
import domain.WinningNumber;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResultRepository {

    private final Map<Rank, Integer> calculateResult;

    private LottoResultRepository() {
        this.calculateResult = new LinkedHashMap<>();
        for (Rank value : Rank.values()) {
            calculateResult.put(value, 0);
        }
    }

    public static LottoResultRepository create() {
        return new LottoResultRepository();
    }

    public void add(WinningNumber winningNumber, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            int matchCount = winningNumber.calculateMatchNumber(lotto);
            boolean hasBonusNumber = winningNumber.hasBonusNumber(lotto);
            Rank rank = Rank.findRank(matchCount, hasBonusNumber);
            calculateResult.put(rank, calculateResult.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getCalculateResult() {
        return Collections.unmodifiableMap(calculateResult);
    }
}
