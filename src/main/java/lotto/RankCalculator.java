package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RankCalculator {

    private static final int INIT_NUMBER = 0;

    private final Map<Rank, Integer> result = new HashMap<>();

    public RankCalculator() {
        initResult();
    }

    private void initResult() {
        result.put(Rank.FIRST, INIT_NUMBER);
        result.put(Rank.SECOND, INIT_NUMBER);
        result.put(Rank.THIRD, INIT_NUMBER);
        result.put(Rank.FOURTH, INIT_NUMBER);
        result.put(Rank.FIFTH, INIT_NUMBER);
    }

    public Map<Rank, Integer> calcRank(TotalNumber totalNumber, List<Set<LottoNumber>> tickets) {
        Set<LottoNumber> winningAndBonusNumber = totalNumber.getWinningAndBonusNumber();

        for (Set<LottoNumber> ticket : tickets) {
            ticket.retainAll(winningAndBonusNumber);

            if (ticket.size() < 3) {
                continue;
            }
            if (ticket.size() == 6) {
                if (ticket.contains(totalNumber.getBonusNumber())) {
                    result.put(Rank.of(ticket.size() - 1, true),
                            result.get(Rank.SECOND) + 1);
                    continue;
                }
            }

            result.put(Rank.of(ticket.size(), false),
                    result.get(Rank.of(ticket.size(), false)) + 1);
        }

        return result;
    }
}
