package lotto.controller;

import lotto.domain.lottomanager.shufflerule.RandomShuffle;
import lotto.domain.result.WinningResult;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;
import lotto.domain.winning.WinningLotto;
import lotto.view.inputview.InputParser;
import lotto.view.inputview.InputView;
import lotto.view.outputview.OutputView;

import java.util.List;

public class LottoShop {
    public void operate() {
        PurchaseAmount purchaseAmount = PurchaseAmount.createLottoAmount(getPurchasePrice());
        List<String> manualTickets = getManualTickets();
        OutputView.printAmount(purchaseAmount, manualTickets.size());

        UserTickets userTickets = UserTickets.createUserTickets(manualTickets, purchaseAmount, new RandomShuffle());
        OutputView.printUserLottoTickets(userTickets);

        WinningLotto winningLotto = WinningLotto.createWinningLotto(getLottoNum(), getBonusBall());

        WinningResult winningResult = WinningResult.createWinningResult(userTickets, winningLotto);
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
