package lotto.view;

import lotto.domain.lottoTicket.Lottos;
import lotto.domain.rank.Rank;

public class OutputView {
    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }

    public static void printRankState(Rank rank) {
        System.out.println(rank.toString());
    }
}
