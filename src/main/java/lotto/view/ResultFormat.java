package lotto.view;

import lotto.domain.Rank;
import lotto.domain.WinPrize;

import java.util.ArrayList;
import java.util.List;

public class ResultFormat {
    public static List<String> format(final WinPrize winPrize) {
        List<String> messages = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            messages.add(message(winPrize, rank));
        }
        return messages;
    }

    private static String message(final WinPrize winPrize, final Rank rank) {
        if (rank == Rank.MISS) {
            return "";
        }

        return String.format("%d개 일치%s(%d원)- %d개",
                rank.getCountOfMatch(),
                rank.equals(Rank.SECOND) ? ", 보너스 볼 일치" : " ",
                rank.getPrize(),
                winPrize.getWinCount(rank));
    }
}
