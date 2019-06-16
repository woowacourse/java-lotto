package lotto.controller;

import lotto.domain.result.WinningResult;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;
import lotto.domain.lottomanager.WinningLotto;
import lotto.view.inputview.InputParser;
import lotto.view.inputview.InputView;
import lotto.view.outputview.OutputView;

import java.util.List;

public class LottoShop {
    public void operate() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(getPurchasePrice());
        List<String> manualTickets = getManualTickets();
        OutputView.printAmount(purchaseAmount, manualTickets.size());

        UserTickets userTickets = new UserTickets(manualTickets, purchaseAmount);
        OutputView.printUserLottoTickets(userTickets);

        WinningLotto winningLotto = new WinningLotto(getLottoNum(), getBonusBall());

        WinningResult winningResult = new WinningResult(userTickets, winningLotto);
        OutputView.printWinningStatistics(winningResult);
    }

    private List<Integer> getLottoNum() {
        return InputParser.getLottoNum(InputView.inputWinningNum());
    }

    private Integer getPurchasePrice() {
        return InputParser.getPurchasePrice(InputView.inputPrice());
    }

    private List<String> getManualTickets() {
        return InputView.inputManualLottoTickets(InputView.inputManualLottoAmount());
    }

    private int getBonusBall() {
        return InputView.inputBonusBall();
    }
}
