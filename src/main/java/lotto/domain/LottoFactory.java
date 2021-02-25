package lotto.domain;

import java.util.List;

public class LottoFactory {

    private LottoFactory() {
    }

    public static Lottos createManualLottos(Lottos lottos, List<Lotto> lottoTickets) {
        lottos.addManuallyCreatedLottos(lottoTickets);
        return lottos;
    }

    public static Lottos createAutoLottos(Lottos lottos, Purchase purchase) {
        lottos.generateLottoAutomatically(purchase.getAutoPurchase());
        return lottos;
    }
}
