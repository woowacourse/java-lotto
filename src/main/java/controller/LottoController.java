package controller;

import domain.Buyer;
import domain.LottoMatch;
import domain.Money;
import domain.WinningLotto;
import java.util.Map;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Money money = lottoService.createMoney(inputView.inputLottoMoney());
        Buyer buyer = lottoService.buyLotto(money);
        outputView.displayLottos(money.calculateTotalLotto(), buyer.createResult());

        WinningLotto winningLotto = lottoService.createWinningLotto(
                inputView.inputWinningNumbers(),
                inputView.inputBonusNumber()
        );
        Map<LottoMatch, Integer> lottoResult = lottoService.calculateLottoResults(buyer, winningLotto);
        outputView.displayResult(lottoResult);

        double profit = lottoService.calculateProfit(money, lottoResult);
        outputView.displayProfit(profit);
    }
}
