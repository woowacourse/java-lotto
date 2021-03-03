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
        ManualGenerator manualGenerator = new ManualGenerator();
        AutoGenerator autoGenerator = new AutoGenerator();
        LottoGroup lottos = buyLotto(manualGenerator, autoGenerator, manualCount, autoCount);
        WinningLotto winningLotto = checkWinningLotto(manualGenerator);
        drawLotto(winningLotto, lottos, money);
    }

    private LottoGroup buyLotto(ManualGenerator manualGenerator, AutoGenerator autoGenerator, Count manualCount, Count autoCount) {
        List<String> lottoNumbers = InputView.requestManualLotto(manualCount.getCount());
        LottoGroup manualLotto = manualGenerator.group(lottoNumbers);
        LottoGroup autoLotto = autoGenerator.group(autoCount.getCount());
        LottoGroup lottos = manualLotto.merge(autoLotto);

        OutputView.buyLottoMessage(manualCount.getCount(), autoCount.getCount());
        OutputView.printLottos(lottos.getLottoGroup());
        return lottos;
    }

    private WinningLotto checkWinningLotto(ManualGenerator manualGenerator) {
        String winningLottoInput = InputView.requestWinningNumber();
        String bonusBallInput = InputView.requestBonusBall();
        return new WinningLotto(
            manualGenerator.generate(winningLottoInput),
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
