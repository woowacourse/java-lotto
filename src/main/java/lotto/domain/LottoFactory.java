package lotto.domain;

import java.util.List;

public class LottoFactory {

    private LottoFactory() {
    }

    public static void createManualLottos(Lottos lottos, List<Lotto> lottoTickets) {
        lottos.addAll(lottoTickets);
    }

    public static void createAutoLottos(Lottos lottos, int counts) {
        for (int i = 0; i < counts; i++) {
            lottos.add(RandomNumberGenerator.makeTicket());
        }
    }
}
