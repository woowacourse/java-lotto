package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoTicket;
import domain.Money;
import domain.Number;
import domain.WinningNumbers;
import domain.WinningResult;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoFactory lottoFactory;

    public LottoController(InputView inputView, OutputView outputView, LottoFactory lottoFactory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoFactory = lottoFactory;
    }

    public void run() {
        Money money = insertMoney();
        LottoTicket lottoTicket = buyLottoTicket(money);
        WinningNumbers winningNumbers = generateWinningNumbers();
        WinningResult winningResult = new WinningResult(lottoTicket.findWinningCountByRank(winningNumbers));
        announceWinningResult(money, winningResult);
    }

    private Money insertMoney() {
        outputView.printRequestMoney();
        return new Money(inputView.requestMoney());
    }

    private LottoTicket buyLottoTicket(Money money) {
        LottoTicket lottoTicket = new LottoTicket(lottoFactory.generateLottoTicket(money));
        outputView.printPurchasedLottoTicket(lottoTicket.getLottoTicket());
        return lottoTicket;
    }

    private WinningNumbers generateWinningNumbers() {
        outputView.printRequestWinningNumbers();
        Lotto winningLotto = Lotto.from(inputView.requestWinningNumbers());

        outputView.printRequestBonusNumber();
        Number bonusNumber = new Number(inputView.requestBonusNumber());

        return new WinningNumbers(winningLotto, bonusNumber);
    }

    private void announceWinningResult(Money money, WinningResult winningResult) {
        outputView.printWinningResult(winningResult.getWinningResult());
        outputView.printRateOfProfit(winningResult.getRateOfProfit(money));
    }
}
