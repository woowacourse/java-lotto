package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.Store;
import lotto.domain.WinnerLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money inputMoney = InputView.inputMoney();

        Lottos lottos = new Lottos(buyLottos(new Store(inputMoney)));
        OutputView.printLottos(lottos.getLottos());

        OutputView.printRate(sumTotalReward(lottos), inputMoney);
    }

    private List<Lotto> buyLottos(Store store) {
        int amount = InputView.inputManualLottoAmount();
        store.minusManualLottoPrice(amount);

        List<Lotto> manualLottos = InputView.inputManualLottoNumbers(amount);
        List<Lotto> autoLottos = store.buyLottos();
        OutputView.printLottosSize(manualLottos.size(), autoLottos.size());

        return Stream.concat(manualLottos.stream(), autoLottos.stream())
            .collect(Collectors.toList());
    }

    private WinnerLotto createWinnerLotto(List<LottoNumber> winnerLottoNumbers, LottoNumber bonusLottoNumber) {
        return new WinnerLotto(new Lotto(winnerLottoNumbers), bonusLottoNumber);
    }

    private long sumTotalReward(Lottos lottos) {
        List<Rank> ranks = lottos.match(createWinnerLotto(InputView.inputWinnerNumbers(), InputView.inputBonusNumber()));
        OutputView.printRanks(ranks);
        return Rank.calculateReward(ranks);
    }
}
