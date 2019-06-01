package lotto.view;

import lotto.domain.lottoTicket.Lottos;

public class OutputView {
    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }
}
