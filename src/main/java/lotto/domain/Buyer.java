package lotto.domain;

import java.util.List;

public class Buyer {
    private List<Lotto> lottos;

    public Buyer(int lottoTicketCount) {
        createLottos(lottoTicketCount);
    }

    public void createLottos(int lottoTicketCount) {
        new LottoFactory();
        for (int i = 0; i < lottoTicketCount; i++) {
            this.lottos.add(new Lotto(LottoFactory.createLottoNumbers()));
        }
    }
}
