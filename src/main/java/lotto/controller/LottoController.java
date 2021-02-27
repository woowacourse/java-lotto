package lotto.controller;

import lotto.domain.*;
import lotto.exception.LottoCustomException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Map;
import java.util.Set;

public class LottoController {

    private final InputView inputView;

    public LottoController() {
        inputView = new InputView();
    }

    public void run() {
        final Money money = inputMoney();
        final Purchase purchase = inputManualPurchase(money);
        final Lottos lottos = createLottos(purchase);
        final WinningLotto winningLotto = makeWinningTicket();
        final Map<Rank, Integer> result = LottoResultMachine.confirmResult(lottos, winningLotto);

        OutputView.printTotalWinningResult(result);
        OutputView.printProfit(LottoProfit.ofProfit(result, money));
        manageChange(money);
    }

    private Money inputMoney() {
        try {
            return new Money(inputView.inputMoney());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private Purchase inputManualPurchase(Money money) {
        try {
            return new Purchase(money, inputView.inputCountOfManualPurchase());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualPurchase(money);
        }
    }

    private Lottos createLottos(Purchase purchase) {
        Lottos lottos = new Lottos();
        if (purchase.existManualPurchase()) {
            createManualLottos(lottos, purchase.getManualPurchase());
        }
        LottoFactory.createAutoLottos(lottos, purchase.getAutoPurchase());
        OutputView.printAllTickets(purchase, lottos);
        return lottos;
    }


    private void createManualLottos(Lottos lottos, int counts) {
        OutputView.printTitleOfInputManualLotto();
        for (int i = 0; i < counts; i++) {
            LottoFactory.createManualLottos(lottos, inputManualNumbers());
        }
    }

    private Set<LottoNumber> inputManualNumbers() {
        try {
            return inputView.inputLottoNumbers();
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualNumbers();
        }
    }

    private WinningLotto makeWinningTicket() {
        try {
            OutputView.printTitleOfInputWinningLotto();
            return new WinningLotto(
                    LottoFactory.makeLotto(inputView.inputLottoNumbers()),
                    LottoNumber.from(inputView.inputBonusNumber())
            );
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeWinningTicket();
        }
    }

    private void manageChange(Money money) {
        if (money.hasChange()) {
            OutputView.printAboutChange(money.getChange());
        }
    }
}
