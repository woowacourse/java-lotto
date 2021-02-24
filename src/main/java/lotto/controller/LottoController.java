package lotto.controller;

import java.util.List;
import java.util.Map;

import lotto.domain.*;
import lotto.view.LottoView;

public class LottoController {

    public void run() {
        Money money = payMoney();
        List<Lotto> lottos = buyLotto(money);
        WinningLotto winningLotto = checkWinningLotto();
        drawLotto(winningLotto, lottos, money);
    }

    private Money payMoney() {
        return new Money(LottoView.requestMoney());
    }

    private List<Lotto> buyLotto(Money money) {
        Seller seller = new Seller();
        int count = money.count();
        List<Lotto> lottos = seller.sell(count);
        LottoView.buyLotto(count);
        LottoView.printLottos(lottos);
        return lottos;
    }

    private WinningLotto checkWinningLotto() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        return new WinningLotto(
                new Lotto(lottoGenerator.generateManual(LottoView.requestWinningNumber())),
                LottoNumber.of(LottoView.requestBonusBallNumber())
        );
    }

    public void drawLotto(WinningLotto winningLotto, List<Lotto> lottos, Money money) {
        LottoResult lottoResult = new LottoResult();
        Map<Rank, Integer> ranks = lottoResult.matchRank(winningLotto, lottos);
        LottoView.displayResultMessage();
        ranks.forEach((rank, rankCount) -> {
            LottoView.displayResult(rank, rankCount);
        });
        LottoView.displayEarningRate(lottoResult.findEarningRate(money.getMoney()));
    }
}
