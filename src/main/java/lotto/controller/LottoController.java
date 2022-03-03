package lotto.controller;

import java.util.Collections;
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

    private final Store store;

    public LottoController(Store store) {
        this.store = store;
    }

    public void run() {
        LottoPurchaseMoney money = new LottoPurchaseMoney(InputView.inputMoney());
        Lottos lottos = purchaseLottos(money);
        OutputView.printLottos(lottos.getLottos());
        OutputView.printRate(sumTotalReward(lottos), money.getMoney());
    }

    private Lottos purchaseLottos(LottoPurchaseMoney money) {
        int manualAmount = InputView.inputManualLottoAmount();

        List<Lotto> manualLottos = purchaseManualLottos(money, manualAmount);
        List<Lotto> autoLottos = store.buyAutoLotto(money.calculateAvailablePurchaseAmount(manualAmount));
        OutputView.printLottosSize(manualLottos.size(), autoLottos.size());

        return new Lottos(concatLottos(manualLottos, autoLottos));
    }

    private List<Lotto> purchaseManualLottos(LottoPurchaseMoney money, int manualAmount) {
        if (money.isPurchaseLotto(manualAmount)) {
            return store.buyManualLotto(manualAmount);
        }
        return Collections.emptyList();
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
        return new WinnerLotto(new Lotto(winnerLottoNumbers), bonusLottoNumber);
    }
}
