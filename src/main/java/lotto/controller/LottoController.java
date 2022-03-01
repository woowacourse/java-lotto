package lotto.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.vo.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.Store;
import lotto.domain.WinnerLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        int inputMoney = InputView.inputMoney();
        Lottos lottos = new Lottos(buyLottos(new Store(inputMoney)));
        OutputView.printLottos(lottos.getLottos());
        OutputView.printRate(sumTotalReward(lottos), inputMoney);
    }

    private List<Lotto> buyLottos(Store store) {
        List<Lotto> manualLottos = buyManualLotto(store);
        List<Lotto> autoLottos = store.buyAutoLottos();
        OutputView.printLottosSize(manualLottos.size(), autoLottos.size());
        return concatLottos(manualLottos, autoLottos);
    }

    private List<Lotto> buyManualLotto(Store store) {
        int amount = InputView.inputManualLottoAmount();

        if (store.isBuyManualLotto(amount)) {
            store.buyManualLottos(amount);
            return InputView.inputManualLottoNumbers(amount);
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
