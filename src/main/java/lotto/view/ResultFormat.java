package lotto.view;

import lotto.domain.Rank;
import lotto.domain.WinPrize;

public class ResultFormat {
    public static String format(final Rank rank, final WinPrize winPrize) {
        if (rank == Rank.MISS)
            return "";

        return String.format("%d개 일치%s(%d원)- %d개",
                rank.getCountOfMatch(),
                rank.equals(Rank.SECOND) ? ", 보너스 볼 일치" : " ",
                rank.getPrize(),
                winPrize.getWinCount(rank));
    }
}
