package lotto.controller;

import java.util.List;
import java.util.Optional;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoMoneyFactory;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.domain.WinningLottoFactory;
import lotto.util.ObjectCreator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private final LottoFactory lottoFactory = new LottoFactory();
    private final LottoMoneyFactory lottoMoneyFactory = new LottoMoneyFactory();
    private final WinningLottoFactory winningLottoFactory = new WinningLottoFactory();

    public void run() {
        LottoMoney lottoMoney = storeLottoMoney();

        LottoMachine lottoMachine = buyLottoTickets(lottoMoney);
        List<Lotto> lottoTickets = lottoMachine.getLottoTickets();
        OutputView.writeLottoTickets(lottoTickets);

        WinningLotto winningLotto = storeWinningLotto();

        LottoResult lottoResult = checkLottoResult(winningLotto, lottoTickets, lottoMoney);
        OutputView.writeLottoResult(lottoResult);
    }

    private LottoMoney storeLottoMoney() {
        return ObjectCreator.repeatUntilSuccess(this::receiveLottoMoney);
    }

    private Optional<LottoMoney> receiveLottoMoney() {
        return ObjectCreator.useInputToCreateObject(() -> {
            String money = InputView.readLottoMoney();

            return lottoMoneyFactory.createLottoMoney(money);
        });
    }

    private LottoMachine buyLottoTickets(LottoMoney lottoMoney) {
        return ObjectCreator.repeatUntilSuccess(() -> receiveLottoMachine(lottoMoney));
    }

    private Optional<LottoMachine> receiveLottoMachine(LottoMoney lottoMoney) {
        return ObjectCreator.useInputToCreateObject(() -> new LottoMachine(lottoMoney));
    }

    private WinningLotto storeWinningLotto() {
        Lotto winningNumbers = storeWinningLottoNumbers();

        return storeWinningLottoBonus(winningNumbers);
    }

    private Lotto storeWinningLottoNumbers() {
        return ObjectCreator.repeatUntilSuccess(this::receiveWinningLottoNumbers);
    }

    private Optional<Lotto> receiveWinningLottoNumbers() {
        return ObjectCreator.useInputToCreateObject(() -> {
            String numbers = InputView.readWinningNumbers();

            return lottoFactory.createLotto(numbers);
        });
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        return ObjectCreator.repeatUntilSuccess(() -> receiveWinningLottoBonus(winningNumbers));
    }

    private Optional<WinningLotto> receiveWinningLottoBonus(Lotto winningNumbers) {
        return ObjectCreator.useInputToCreateObject(() -> {
            String bonus = InputView.readBonusBall();

            return winningLottoFactory.createWinningLotto(winningNumbers, bonus);
        });
    }

    private LottoResult checkLottoResult(WinningLotto winningLotto, List<Lotto> lottoTickets, LottoMoney lottoMoney) {
        LottoResult lottoResult = new LottoResult();

        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);

        return lottoResult;
    }
}
