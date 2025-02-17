package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.Money;
import lotto.domain.Profit;
import lotto.domain.WinnerLotto;
import lotto.service.LottoService;
import lotto.utils.RecoveryUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;

    public LottoController(LottoService lottoService, InputView inputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
    }

    public void run() {
        Money money = getMoneyWithRetry();
        LottoGroup lottoGroup = lottoService.generateLottoGroupByMoney(money);
        OutputView.printLottoGroup(money.getLottoTicketCount(), lottoService.getLottoGroupMessage(lottoGroup));

        Lotto winnerNumber = getWinnerNumberWithRetry();
        WinnerLotto winnerLotto = getWinnerLottoWithRetry(winnerNumber);

        Profit profit = lottoService.calculateProfit(winnerLotto, lottoGroup);
        OutputView.printStatics(lottoService.getStatusValues(profit), lottoService.getProfitRate(profit, money));
    }

    private Money getMoneyWithRetry() {
        return RecoveryUtils.executeWithRetry(() -> lottoService.getMoney(inputView.readMoney()));
    }

    private Lotto getWinnerNumberWithRetry() {
        return RecoveryUtils.executeWithRetry(() -> lottoService.getWinnerNumber(inputView.readWinnerNumbers()));
    }

    private WinnerLotto getWinnerLottoWithRetry(Lotto winnerNumber) {
        return RecoveryUtils.executeWithRetry(
                () -> lottoService.getWinnerLotto(winnerNumber, inputView.readBonusNumber()));
    }
}
