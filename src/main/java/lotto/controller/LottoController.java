package lotto.controller;

import java.util.List;
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

        Lottos lottos = new Lottos(buyLottos(inputMoney));
        OutputView.printLottos(lottos.getLottos());

        List<Rank> ranks = lottos.match(createWinnerLotto(InputView.inputWinnerNumbers(), InputView.inputBonusNumber()));
        OutputView.printRanks(ranks);

        Money totalReward = Rank.calculateReward(ranks);
        OutputView.printRate(totalReward, inputMoney);
    }

    private List<Lotto> buyLottos(Money money) {
        Store store = new Store(money);
        int amount = InputView.inputPassivityLottoAmount();
        List<Lotto> lottos = store.buyLottos(InputView.inputPassivityLottoNumbers(amount));
        OutputView.printLottosSize(amount, lottos.size());
        return lottos;
    }

    private WinnerLotto createWinnerLotto(List<LottoNumber> winnerLottoNumbers, LottoNumber bonusLottoNumber) {
        return new WinnerLotto(new Lotto(winnerLottoNumbers), bonusLottoNumber);
    }
}
