package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.*;
import lotto.view.*;

public class LottoController {

    public void run() {
        Money money = new Money(InputView.requestMoney());
        Count manualCount = new Count(InputView.requestManualCount());
        Count autoCount = new Count(money.count() - manualCount.getCount());
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoGroup lottos = buyLotto(lottoGenerator, manualCount, autoCount);
        WinningLotto winningLotto = checkWinningLotto(lottoGenerator);
        drawLotto(winningLotto, lottos, money);
    }

    private LottoGroup buyLotto(LottoGenerator lottoGenerator, Count manualCount, Count autoCount) {
        List<String> lottoNumbers = InputView.requestManualLotto(manualCount.getCount());
        LottoGroup manualLotto = lottoGenerator.manualLotto(lottoNumbers);
        LottoGroup autoLotto = lottoGenerator.autoLotto(autoCount.getCount());
        LottoGroup lottos = manualLotto.merge(autoLotto);

        OutputView.buyLottoMessage(manualCount.getCount(), autoCount.getCount());
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
        Map<Rank, Integer> result = lottos.matchRank(winningLotto);
        LottoResult lottoResult = new LottoResult(result);
        OutputView.displayResultMessage();
        lottoResult.getLottoResult().forEach((rank, rankCount) -> {
            OutputView.displayResult(rank.getMatchCount(), rank.getPrize(), rankCount);
        });
        OutputView.displayEarningRate(lottoResult.findEarningRate(money.getMoney()));
    }
}
