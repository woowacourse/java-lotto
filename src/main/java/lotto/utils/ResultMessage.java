package lotto.utils;

import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.Rank.SECOND;

public class ResultMessage {

    public static List<String> getResult(LottoResult lottoResult,List<Rank> ranks) {
        List<String> rankers = new ArrayList<>();
        ranks.stream().forEach(rank -> {
            rankers.add(String.format(getMessage(rank),rank.getCountOfMatch(),rank.getWinningMoney(),lottoResult.getCountOfRanker(rank)));
        });

        return rankers;
    }

    private static String getMessage(Rank rank) {
        return rank == SECOND ? "%d개 일치, 보너스 볼 일치(%d원) - %d개\n" : "%d개 일치(%d원) - %d개\n";
    }

}
