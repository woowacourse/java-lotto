package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.*;
import lotto.view.*;

public class LottoController {

    public void run() {
        Money money = new Money(InputView.requestMoney());
        Count count = new Count(money.count(), InputView.requestManualCount());
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoGroup lottos = buyLotto(lottoGenerator, count);
        WinningLotto winningLotto = checkWinningLotto(lottoGenerator);
        drawLotto(winningLotto, lottos, money);
    }

    private LottoGroup buyLotto(LottoGenerator lottoGenerator, Count count) {
        int manualCount = count.getManualCount();
        int autoCount = count.getAutoCount();

        List<String> lottoNumbers = InputView.requestManualLotto(manualCount);
        LottoGroup manualLotto = lottoGenerator.manualLotto(lottoNumbers);
        LottoGroup autoLotto = lottoGenerator.autoLotto(autoCount);
        LottoGroup lottos = manualLotto.merge(autoLotto);

        OutputView.buyLottoMessage(count.getManualCount(), count.getAutoCount());
        OutputView.printLottos(lottos.getLottoGroup());
        return lottos;
    }

    private WinningLotto checkWinningLotto(LottoGenerator lottoGenerator) {
        String winningLottoInput = InputView.requestWinningNumber();
        String bonusBallInput = InputView.requestBonusBall();
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
