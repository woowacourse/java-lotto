package lotto.controller;

import lotto.domain.Rank;
import lotto.dto.StatDto;

import java.util.ArrayList;
import java.util.List;

public class StatStringifier {
    public String stringifyProfit(StatDto statDto) {
        return String.format("%.1f", statDto.getProfit());
    }

    public List<String> stringifyResult(final StatDto stat) {
        List<String> results = new ArrayList<>();
        for (Rank rank : Rank.reverseValues()) {
            results.add(stringifyRank(rank) + stat.getCount(rank) + "개");
        }
        return results;
    }

    private String stringifyRank(final Rank rank) {
        StringBuilder sb = new StringBuilder();
        sb.append(rank.getMatchCount() + "개 일치");
        if (rank == Rank.SECOND) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append("(" + rank.getMoney() + ")- ");
        return sb.toString();
    }
}
