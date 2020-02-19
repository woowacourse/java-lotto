package lotto.domain;

import java.util.List;

public class Buyer {
    private List<Lotto> lottos;
    private int money;
    private int lottoTicketCount;

    public Buyer(int money) {
        this.money = money;
        this.lottoTicketCount = PurchaseLotto.purchase(money);
        createLottos();
    }

    public void createLottos() {
        new LottoFactory();
        for (int i = 0; i < lottoTicketCount; i++) {
            this.lottos.add(new Lotto(LottoFactory.createLottoNumbers()));
        }
    }
}
