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
        OutputView.requestMoneyMessage();
        return new Money(InputView.inputMoney());
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
        OutputView.requestWinningNumberMessage();
        String winningLottoInput = InputView.requestInput();
        OutputView.requestBonusBallNumberMessage();
        String bonusBallInput = InputView.requestInput();
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
