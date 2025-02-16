package lotto.controller;

import java.util.List;
import java.util.Optional;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.service.LottoMoneyService;
import lotto.service.LottoService;
import lotto.service.WinningLottoService;
import lotto.util.ObjectCreator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private final LottoService lottoService = new LottoService();
    private final LottoMoneyService lottoMoneyService = new LottoMoneyService();
    private final WinningLottoService winningLottoService = new WinningLottoService();

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
        Optional<LottoMoney> lottoMoney = Optional.empty();

        while (lottoMoney.isEmpty()) {
            lottoMoney = createLottoMoney();
        }
        return lottoMoney.get();
    }

    private Optional<LottoMoney> createLottoMoney() {
        return ObjectCreator.useInputToCreateObject(() -> {

            String money = InputView.readLottoMoney();

            return lottoMoneyService.createLottoMoney(money);
        });
    }

    private LottoMachine buyLottoTickets(LottoMoney lottoMoney) {
        Optional<LottoMachine> lottoMachine = Optional.empty();

        while (lottoMachine.isEmpty()) {
            lottoMachine = ObjectCreator.useInputToCreateObject(() -> new LottoMachine(lottoMoney));
        }
        return lottoMachine.get();
    }

    private WinningLotto storeWinningLotto() {
        Lotto winningNumbers = storeWinningLottoNumbers();

        return storeWinningLottoBonus(winningNumbers);
    }

    private Lotto storeWinningLottoNumbers() {
        Optional<Lotto> lotto = Optional.empty();

        while (lotto.isEmpty()) {
            lotto = createWinningLottoNumbers();
        }
        return lotto.get();
    }

    private Optional<Lotto> createWinningLottoNumbers() {
        return ObjectCreator.useInputToCreateObject(() -> {

            String numbers = InputView.readWinningNumbers();

            return lottoService.createLotto(numbers);
        });
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        Optional<WinningLotto> winningLotto = Optional.empty();

        while (winningLotto.isEmpty()) {
            winningLotto = createWinningLottoWithBonus(winningNumbers);
        }
        return winningLotto.get();
    }

    private Optional<WinningLotto> createWinningLottoWithBonus(Lotto winningNumbers) {
        return ObjectCreator.useInputToCreateObject(() -> {

            String bonus = InputView.readBonusBall();

            return winningLottoService.createWinningLotto(winningNumbers, bonus);
        });
    }

    private LottoResult checkLottoResult(WinningLotto winningLotto, List<Lotto> lottoTickets, LottoMoney lottoMoney) {
        LottoResult lottoResult = new LottoResult();

        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);

        return lottoResult;
    }
}
