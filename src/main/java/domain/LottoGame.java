package domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoGame {
    public static Statistics startLottery(IssuedLottos issuedLottos, WinningLotto winningLotto) {
        Map<Rank, CountOfRank> countsOfRank = getEnumMapOfRankCounts();

        for (IssuedLotto issuedLotto : issuedLottos.getLottos()) {
            Rank matchingResult = winningLotto.matchUpLottoNumbersWith(issuedLotto);
            addRankToCountsOfRank(matchingResult, countsOfRank);
        }

        return Statistics.of(countsOfRank, issuedLottos.getPurchasedAmount());
    }

    private static Map<Rank, CountOfRank> getEnumMapOfRankCounts() {
        Map<Rank, CountOfRank> countsOfRank = new EnumMap<>(Rank.class);

        initialize(countsOfRank, Rank.values());
        return countsOfRank;
    }

    private static void initialize(Map<Rank, CountOfRank> countsOfRanks, Rank[] values) {
        for (Rank rank : values) {
            countsOfRanks.put(rank, new CountOfRank());
        }
    }

    private static void addRankToCountsOfRank(Rank matchingResult, Map<Rank, CountOfRank> countsOfRank) {
        countsOfRank.get(matchingResult).countUp();
    }


}
