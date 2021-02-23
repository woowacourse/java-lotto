package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.*;
import lotto.view.LottoView;

public class LottoController {
    private Seller seller;
    private Money money;
    private List<Lotto> lottos;

    public void run() {
        this.seller = new Seller();

    }

    public void buyLotto() {
        this.money =  new Money(LottoView.requestMoney());
        int count = money.count();
        this.lottos = seller.sell(count);
        LottoView.buyLotto(count);
        LottoView.printLottos(lottos);
    }

    public void drawLotto() {
        WinningLotto winningLotto = new WinningLotto(
            new Lotto(seller.sell(LottoView.requestWinningNumber())),
            LottoNumber.of(LottoView.requestBonusBallNumber())
        );
        LottoResult lottoResult = new LottoResult();
        Map<Rank, Integer> ranks = lottoResult.matchRank(winningLotto, lottos);

        LottoView.displayResultMessage();
        ranks.forEach((rank, rankCount) -> {
            LottoView.displayResult(rank, rankCount);
        });
        LottoView.displayEarningRate(lottoResult.findEarningRate(money.getMoney()));
    }
}
