package lotto.controller;

import java.util.Map;
import lotto.domain.*;
import lotto.view.*;

public class LottoController {

    public void run() {
        Money money = payMoney();
        LottoGroup lottos = buyLotto(money);
        WinningLotto winningLotto = checkWinningLotto();
        drawLotto(winningLotto, lottos, money);
    }

    private Money payMoney() {
        return new Money(InputView.requestMoney());
    }

    private LottoGroup buyLotto(Money money) {
        Seller seller = new Seller();
        int count = money.count();
        LottoGroup lottos = seller.sellAuto(count);
        OutputView.buyLottoMessage(count);
        OutputView.printLottos(lottos.getLottoGroup());
        return lottos;
    }

    private WinningLotto checkWinningLotto() {
        String winningLottoInput = InputView.requestWinningNumber();
        String bonusBallInput = InputView.requestBonusBall();
        LottoGenerator lottoGenerator = new LottoGenerator();
        return new WinningLotto(
            lottoGenerator.generateManual(winningLottoInput),
            LottoNumber.of(bonusBallInput)
        );
    }

    public void drawLotto(WinningLotto winningLotto, LottoGroup lottos, Money money) {
        LottoResult lottoResult = new LottoResult();
        Map<Rank, Integer> ranks = lottoResult.matchRank(winningLotto, lottos);
        OutputView.displayResultMessage();
        ranks.forEach((rank, rankCount) -> {
            OutputView.displayResult(rank.getMatchCount(), rank.getPrize(), rankCount);
        });
        OutputView.displayEarningRate(lottoResult.findEarningRate(money.getMoney()));
    }
}
