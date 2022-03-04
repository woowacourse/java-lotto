package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.Lottos;
import lotto.domain.LottosFactory;
import lotto.domain.Rank;
import lotto.domain.WinnerLotto;
import lotto.domain.vo.LottoNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoPurchaseMoney money = new LottoPurchaseMoney(InputView.inputMoney());
        Lottos lottos = purchaseLottos(money);
        OutputView.printLottos(lottos.getLottos());
        OutputView.printRate(sumTotalReward(lottos), money.getMoney());
    }

    private Lottos purchaseLottos(LottoPurchaseMoney money) {
        int manualAmount = InputView.inputManualLottoAmount();

        List<Lotto> manualLottos = LottosFactory.MANUAL.generate(manualAmount);
        List<Lotto> autoLottos = LottosFactory.AUTO.generate(money.calculateAvailablePurchaseAmount(manualAmount));
        OutputView.printLottosSize(manualLottos.size(), autoLottos.size());

        return new Lottos(concatLottos(manualLottos, autoLottos));
    }

    private List<Lotto> concatLottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        return Stream.concat(manualLottos.stream(), autoLottos.stream())
            .collect(Collectors.toList());
    }

    private long sumTotalReward(Lottos lottos) {
        List<Rank> ranks = lottos.match(createWinnerLotto(InputView.inputWinnerNumbers(), InputView.inputBonusNumber()));
        OutputView.printRanks(ranks);
        return Rank.calculateReward(ranks);
    }

    private WinnerLotto createWinnerLotto(List<LottoNumber> winnerLottoNumbers, LottoNumber bonusLottoNumber) {
        return new WinnerLotto(Lotto.of(winnerLottoNumbers), bonusLottoNumber);
    }
}
