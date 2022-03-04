package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinnerLotto;
import lotto.domain.vo.LottoNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoPurchaseMoney money = InputView.inputMoney();

        int manualAmount = InputView.inputManualLottoAmount();

        List<Lotto> manualLottos = LottosFactory.MANUAL.generate(manualAmount);
        List<Lotto> autoLottos = LottosFactory.AUTO.generate(money.calculateAvailablePurchaseAmount(manualAmount));
        OutputView.printLottosSize(manualLottos.size(), autoLottos.size());

        Lottos lottos = new Lottos(concatLottos(manualLottos, autoLottos));
        OutputView.printLottos(lottos.getLottos());

        List<Rank> ranks = lottos.match(createWinnerLotto(InputView.inputWinnerNumbers(), InputView.inputBonusNumber()));
        OutputView.printRanks(ranks);
        OutputView.printRate(Rank.calculateReward(ranks), money.getMoney());
    }

    private List<Lotto> concatLottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        return Stream.concat(manualLottos.stream(), autoLottos.stream())
            .collect(Collectors.toList());
    }

    private WinnerLotto createWinnerLotto(List<LottoNumber> winnerLottoNumbers, LottoNumber bonusLottoNumber) {
        return new WinnerLotto(Lotto.of(winnerLottoNumbers), bonusLottoNumber);
    }
}
